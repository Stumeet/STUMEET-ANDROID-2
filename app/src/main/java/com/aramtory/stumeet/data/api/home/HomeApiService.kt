package com.aramtory.stumeet.data.api.home

import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.res.home.AlarmResDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val NOTIFICATION = "notification"
        const val LOGS = "logs"
    }

    // 8.2 멤버 알림 기록 목록 조회
    @GET("/${API}/${V1}/${NOTIFICATION}/${LOGS}")
    suspend fun getAlarmList(
        @Query("size") size: Int,
        @Query("page") page: Int,
    ): BaseResponseNullable<AlarmResDto>
}