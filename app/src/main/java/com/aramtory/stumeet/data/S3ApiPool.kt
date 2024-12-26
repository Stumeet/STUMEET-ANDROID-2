package com.aramtory.stumeet.data

import android.util.Log
import com.aramtory.stumeet.BuildConfig.BASE_URL
import com.aramtory.stumeet.data.api.study.StudyActivityApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.aramtory.stumeet.data.API.API_TAG
import com.aramtory.stumeet.data.api.file.FileApiService
import com.aramtory.stumeet.data.api.file.S3ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit

object S3ApiPool {
    private var s3ApiServiceInstance: S3ApiService? = null

    fun initialize(baseUrl: String) {
        s3ApiServiceInstance = S3RetrofitPool.createRetrofit(baseUrl).create(S3ApiService::class.java)
    }

    val s3ApiService: S3ApiService
        get() = s3ApiServiceInstance ?: throw IllegalStateException("S3ApiPool is not initialized. Call initialize(baseUrl) first.")
}

object S3RetrofitPool {
    fun createRetrofit(baseUrl: String): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d(API_TAG, JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Log.d(API_TAG, JSONArray(message).toString(4))

                else -> {
                    Log.d(API_TAG, "CONNECTION INFO -> $message")
                }
            }
        }

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }
}

