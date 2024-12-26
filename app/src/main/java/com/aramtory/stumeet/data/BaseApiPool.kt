package com.aramtory.stumeet.data

import android.util.Log
import com.aramtory.stumeet.BuildConfig.BASE_URL
import com.aramtory.stumeet.data.API.API_TAG
import com.aramtory.stumeet.data.api.file.FileApiService
import com.aramtory.stumeet.data.api.file.S3ApiService
import com.aramtory.stumeet.data.api.home.HomeApiService
import com.aramtory.stumeet.data.api.my.MyApiService
import com.aramtory.stumeet.data.api.signup.SignUpApiService
import com.aramtory.stumeet.data.api.study.StudyActivityApiService
import com.aramtory.stumeet.data.api.study.StudyApiService
import com.aramtory.stumeet.data.api.study.StudyMemberApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit

object BaseApiPool {
    val postFile: FileApiService = BaseRetrofitPool.retrofit.create(FileApiService::class.java)
    val putS3: S3ApiService = BaseRetrofitPool.retrofit.create(S3ApiService::class.java)
    val getHome: HomeApiService = BaseRetrofitPool.retrofit.create(HomeApiService::class.java)
    val getMy: MyApiService = BaseRetrofitPool.retrofit.create(MyApiService::class.java)
    val getSignUp: SignUpApiService = BaseRetrofitPool.retrofit.create(SignUpApiService::class.java)
    val getStudyActivity: StudyActivityApiService = BaseRetrofitPool.retrofit.create(StudyActivityApiService::class.java)
    val getStudy: StudyApiService = BaseRetrofitPool.retrofit.create(StudyApiService::class.java)
    val getStudyMember: StudyMemberApiService = BaseRetrofitPool.retrofit.create(StudyMemberApiService::class.java)
}

object BaseRetrofitPool {
    val retrofit: Retrofit by lazy {
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

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }
}

object API {
    const val API_TAG = "Retrofit2"
}