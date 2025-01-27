package com.aramtory.stumeet.presentation.auth.login

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class KakaoLoginService(private val context: Context) {
    fun startKakaoLogin(kakaoLoginCallBack: (OAuthToken?, Throwable?) -> Unit) {
        val kakaoLoginState =
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                KAKAO_APP_LOGIN
            } else {
                KAKAO_ACCOUNT_LOGIN
            }

        when (kakaoLoginState) {
            KAKAO_APP_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = kakaoLoginCallBack,
                )
            }

            KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = kakaoLoginCallBack,
                )
            }
        }
    }

    companion object {
        const val KAKAO_APP_LOGIN = 0
        const val KAKAO_ACCOUNT_LOGIN = 1
    }
}