package com.ch2_ps397.destinology.ui.screen.recommendation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2_ps397.destinology.core.data.repository.ItineraryRepository
import com.ch2_ps397.destinology.core.model.MItinerary
import com.ch2_ps397.destinology.core.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DestinologyRecommendationViewModel(
    private val itineraryRepository: ItineraryRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _resource: MutableStateFlow<Resource<List<MItinerary>>> = MutableStateFlow(Resource.Idle)
    val resource: MutableStateFlow<Resource<List<MItinerary>>> = _resource

    fun generateNewItinerary(city: String, duration: Int, budget: Int) {
        viewModelScope.launch {
            itineraryRepository.generateNewItinerary(city, duration, budget)
                .catch { cause ->
                    _resource.value = Resource.Error(cause.message.toString())
                }
                .collect { data ->
                    savedStateHandle["itinerary"] = data
                    _resource.value = Resource.Success(data)
                }
        }
    }
}