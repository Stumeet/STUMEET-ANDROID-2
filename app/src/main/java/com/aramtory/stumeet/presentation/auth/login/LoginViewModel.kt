package com.aramtory.stumeet.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramtory.stumeet.coreui.view.UiState
import com.aramtory.stumeet.data.SharedManager
import com.aramtory.stumeet.data.api.signup.SignUpApiService
import com.aramtory.stumeet.data.dto.req.signup.TokenRefreshReqDto
import com.aramtory.stumeet.data.dto.res.signup.AccessTokenResDto
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.Base64

class LoginViewModel(
    private val kakaoLoginService: KakaoLoginService,
    private val signUpApiService: SignUpApiService
) : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<AccessTokenResDto>>(UiState.Empty)
    val loginState: StateFlow<UiState<AccessTokenResDto>> = _loginState

    private var tokenRefreshJob: Job? = null

    // 카카오 로그인
    fun kakaoLogin() {
        _loginState.value = UiState.Loading
        kakaoLoginService.startKakaoLogin { oAuthToken, error ->
            when {
                error != null -> updateStateWithError("카카오 로그인 실패: ${error.localizedMessage}")
                oAuthToken != null -> postAccessToken(oAuthToken.accessToken)
            }
        }
    }

    // 소셜 로그인
    private fun postAccessToken(oauthToken: String) = viewModelScope.launch {
        _loginState.value = UiState.Loading
        runCatching {
            val response = signUpApiService.postAccessToken("Bearer $oauthToken")
            response.data?.takeIf { it.accessToken.isNotBlank() && it.refreshToken.isNotBlank() }
                ?.also { data ->
                    saveTokens(data.accessToken, data.refreshToken)
                    scheduleTokenRefresh(data.accessToken)
                    _loginState.value = UiState.Success(data)
                } ?: throw IllegalStateException("Invalid server response")
        }.onFailure { e ->
            updateStateWithError("서버 요청 실패: ${e.localizedMessage}")
        }
    }

    // 토큰 재발급
    private fun refreshAccessToken() {
        val refreshToken = SharedManager.getRefreshToken()
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                val response = signUpApiService.refreshToken(
                    TokenRefreshReqDto(
                        accessToken = SharedManager.getAccessToken().orEmpty(),
                        refreshToken = refreshToken
                    )
                )
                response.data?.let { data ->
                    saveTokens(data.accessToken, data.refreshToken)
                    scheduleTokenRefresh(data.accessToken)
                    _loginState.value = UiState.Success(
                        AccessTokenResDto(
                            data.accessToken,
                            false,
                            data.refreshToken
                        )
                    )
                } ?: throw IllegalStateException("서버 응답이 없습니다.")
            }.onFailure { e ->
                updateStateWithError("토큰 재발급 실패: ${e.localizedMessage}")
            }
        }
    }

    // 토큰 저장
    private fun saveTokens(accessToken: String, refreshToken: String) {
        SharedManager.saveTokens(accessToken, refreshToken)
    }

    private fun updateStateWithError(message: String) {
        _loginState.value = UiState.Failure(message)
    }

    // 토큰 자동 갱신
    private fun scheduleTokenRefresh(accessToken: String) {
        tokenRefreshJob?.cancel()
        val delayTime = extractTokenExpiry(accessToken) - TOKEN_REFRESH_BUFFER

        if (delayTime > 0) {
            tokenRefreshJob = viewModelScope.launch {
                delay(delayTime)
                refreshAccessToken() // 토큰 재발급
            }
        }
    }

    // JWT 토큰 만료 시간 추출
    private fun extractTokenExpiry(token: String): Long {
        return try {
            val payload = token.split(".")[1]
            val decodedString = String(Base64.getUrlDecoder().decode(payload))
            val expiryTime = Json.parseToJsonElement(decodedString)
                .jsonObject["exp"]?.jsonPrimitive?.content?.toLongOrNull()
            expiryTime?.times(1000) ?: 0L
        } catch (e: Exception) {
            0L
        }
    }

    companion object {
        private const val TOKEN_REFRESH_BUFFER = 300_000L
    }
}