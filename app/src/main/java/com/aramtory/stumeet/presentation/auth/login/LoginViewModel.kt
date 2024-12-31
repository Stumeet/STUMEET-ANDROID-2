package com.aramtory.stumeet.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramtory.stumeet.coreui.view.UiState
import com.aramtory.stumeet.data.SharedManager
import com.aramtory.stumeet.data.api.signup.SignUpApiService
import com.aramtory.stumeet.data.dto.res.signup.AccessTokenResDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val kakaoLoginService: KakaoLoginService,
    private val signUpApiService: SignUpApiService
) : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<AccessTokenResDto>>(UiState.Empty)
    val loginState: StateFlow<UiState<AccessTokenResDto>> = _loginState

    // 카카오 로그인
    fun kakaoLogin() {
        _loginState.value = UiState.Loading
        kakaoLoginService.startKakaoLogin { oAuthToken, error ->
            when {
                error != null -> updateStateWithError("카카오 로그인 실패: ${error.localizedMessage}")
                oAuthToken != null -> fetchAccessTokenFromServer(oAuthToken.accessToken)
            }
        }
    }

    // 소셜 로그인
    private fun fetchAccessTokenFromServer(oauthToken: String) = viewModelScope.launch {
        _loginState.value = try {
            val response = signUpApiService.postAccessToken("Bearer $oauthToken")
            response.data?.let {
                saveTokens(it)
                UiState.Success(it)
            } ?: UiState.Failure("서버 응답이 올바르지 않습니다.")
        } catch (e: Exception) {
            UiState.Failure("서버 요청 실패: ${e.localizedMessage}")
        }
    }

    // 토큰 저장
    private fun saveTokens(accessTokenResDto: AccessTokenResDto) {
        SharedManager.saveTokens(
            accessToken = accessTokenResDto.accessToken,
            refreshToken = accessTokenResDto.refreshToken
        )
    }

    private fun updateStateWithError(message: String) {
        _loginState.value = UiState.Failure(message)
    }
}