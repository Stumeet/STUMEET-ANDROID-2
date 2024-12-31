package com.aramtory.stumeet.data

import android.content.Context
import android.content.SharedPreferences

object SharedManager {
    private const val PREF_NAME = "my_shared_preferences"
    private const val AUTO_LOGIN = "auto_login"
    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isFirstLogin(value: Boolean) {
        sharedPreferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }

    fun checkLogin(): Boolean = sharedPreferences.getBoolean(AUTO_LOGIN, false)

    fun saveTokens(accessToken: String, refreshToken: String) {
        sharedPreferences.edit().apply {
            putString(ACCESS_TOKEN, accessToken)
            putString(REFRESH_TOKEN, refreshToken)
            apply()
        }
    }

    fun getAccessToken(): String? = sharedPreferences.getString(ACCESS_TOKEN, null)?.let { "Bearer $it" }

    fun getRefreshToken(): String? = sharedPreferences.getString(REFRESH_TOKEN, null)?.let { "Bearer $it" }

    fun clearTokens() {
        sharedPreferences.edit().apply {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            apply()
        }
    }
}