package com.ch2_ps397.destinology.ui.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2_ps397.destinology.core.data.repository.UserRepository
import com.ch2_ps397.destinology.core.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DestinologyUserAuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _resource: MutableStateFlow<Resource<Boolean>> = MutableStateFlow(Resource.Idle)
    val resource: MutableStateFlow<Resource<Boolean>> = _resource

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            userRepository.loginUser(email, password)
        }
    }
    fun registerUser(
        email: String,
        fullname: String,
        username: String,
        password: String
    ) {
       viewModelScope.launch {
           _resource.value = Resource.Loading
           val createAccountRes = userRepository.createAccountUser(
               email = email,
               fullName = fullname,
               username = username,
               password = password
           )

           if (createAccountRes.isSuccessful) {
               _resource.value = Resource.Success(createAccountRes.isSuccessful)
           } else {
               _resource.value = Resource.Error(createAccountRes.errorBody().toString())
           }
       }
    }
}