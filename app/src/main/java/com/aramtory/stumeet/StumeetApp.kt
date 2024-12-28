package com.aramtory.stumeet

import android.app.Application
import com.aramtory.stumeet.BuildConfig.NATIVE_APP_KEY
import com.aramtory.stumeet.data.SharedManager
import com.kakao.sdk.common.KakaoSdk
import timber.log.Timber

class StumeetApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setTimber()
        SharedManager.init(this)

        // Kakao SDK 초기화
        KakaoSdk.init(this, NATIVE_APP_KEY)
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }
}