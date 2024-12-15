package com.aramtory.stumeet.data.api

import com.aramtory.stumeet.data.dto.BaseResponseNullable
import retrofit2.http.GET

interface StudyApiService {
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val STUDIES = "studies"
    }

    @GET("/$V1/$API/$STUDIES")
    suspend fun getSomething(
    ): BaseResponseNullable<Unit>
}