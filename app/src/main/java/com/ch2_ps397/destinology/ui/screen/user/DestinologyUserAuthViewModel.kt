package com.ch2_ps397.destinology.ui.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ch2_ps397.destinology.core.data.repository.UserRepository
import com.ch2_ps397.destinology.core.utils.Resource
import com.ch2_ps397.destinology.navigation.DestinologyScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DestinologyUserAuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _resource: MutableStateFlow<Resource<Any>> = MutableStateFlow(Resource.Idle)
    val resource: MutableStateFlow<Resource<Any>> = _resource

    fun loginUser(email: String, password: String, navController: NavController) {
        viewModelScope.launch {
            try {
                _resource.value = Resource.Loading
                val res = userRepository.loginUser(email, password)
                when (res) {
                    200 -> {
                        _resource.value = Resource.Success("Yay! Berhasil masuk.")
                        navController.navigate(DestinologyScreens.DestinologyPlanScreen.name) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                    400 -> {
                        _resource.value = Resource.Error("Password or email tidak sesuai.")
                    }
                    422 -> {
                        _resource.value = Resource.Error("Akun belum terdaftar.")

                    }
                    else -> {
                        _resource.value = Resource.Error("Ada yang salah. Coba lagi.")
                    }
                }
            } catch (e: Exception) {

            }
        }
    }
    fun registerUser(
        email: String,
        fullname: String,
        username: String,
        password: String,
        navController: NavController
    ) {
        viewModelScope.launch {
            try {
                _resource.value = Resource.Loading
                val res = userRepository.createAccountUser(
                    email = email,
                    fullName = fullname,
                    username = username,
                    password = password
                )
                when (res) {
                    201 -> {
                        _resource.value = Resource.Success("Yay! Akun berhasil terdaftar.")
                        navController.navigate(DestinologyScreens.DestinologyUserLoginScreen.name) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                    400 -> {
                        _resource.value = Resource.Error("Akun sudah terpakai.")
                    }
                    422 -> {
                        _resource.value = Resource.Error("Ada yang salah. Periksa email dan password anda.")

                    }
                    else -> {
                        _resource.value = Resource.Error("Ada yang salah. Coba lagi.")
                    }
                }
            } catch (e: Exception) {

            }
        }
    }
}