package com.aramtory.stumeet.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aramtory.stumeet.data.api.signup.SignUpApiService

class LoginViewModelFactory(
    private val kakaoLoginService: KakaoLoginService,
    private val signUpApiService: SignUpApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(kakaoLoginService, signUpApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}