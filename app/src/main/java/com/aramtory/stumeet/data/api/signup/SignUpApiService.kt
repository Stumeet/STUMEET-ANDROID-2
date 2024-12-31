package com.aramtory.stumeet.data.api.signup

import com.aramtory.stumeet.data.api.ApiKeyStorage.API
import com.aramtory.stumeet.data.api.ApiKeyStorage.AUTH
import com.aramtory.stumeet.data.api.ApiKeyStorage.AUTHORIZATION
import com.aramtory.stumeet.data.api.ApiKeyStorage.KAKAO
import com.aramtory.stumeet.data.api.ApiKeyStorage.MEMBERS
import com.aramtory.stumeet.data.api.ApiKeyStorage.NOTIFICATION_TOKEN
import com.aramtory.stumeet.data.api.ApiKeyStorage.OAUTH_PROVIDE
import com.aramtory.stumeet.data.api.ApiKeyStorage.PROFESSIONS
import com.aramtory.stumeet.data.api.ApiKeyStorage.RENEW
import com.aramtory.stumeet.data.api.ApiKeyStorage.SIGNUP
import com.aramtory.stumeet.data.api.ApiKeyStorage.V1
import com.aramtory.stumeet.data.api.ApiKeyStorage.VALIDATE_NICKNAME
import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.req.signup.NotificationReqDto
import com.aramtory.stumeet.data.dto.res.signup.AccessTokenResDto
import com.aramtory.stumeet.data.dto.res.signup.PartListResDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SignUpApiService {
    /**로그인**/
    // 1.1 소셜 로그인
    @POST("/${API}/${V1}/${AUTH}")
    suspend fun postAccessToken(
        @Header(AUTHORIZATION) oauthAuthorization: String,
        @Header(OAUTH_PROVIDE) provide: String = KAKAO,
    ): BaseResponseNullable<AccessTokenResDto>

    // 8.1 알림 토큰 갱신
    @POST("/${API}/${V1}/${NOTIFICATION_TOKEN}/${RENEW}")
    suspend fun postNotificationToken(
        @Body request: NotificationReqDto,
    ): BaseResponseNullable<Unit>

    /** 회원가입 **/
    // 1.5 사용자 닉네임 유효성 검사
    @GET("/${API}/${V1}/${MEMBERS}/${VALIDATE_NICKNAME}")
    suspend fun getValidateNickname(
        @Query("nickname") nickname: String,
    ): BaseResponseNullable<Unit>

    // 2.1 분야 정보 전체 조회
    @GET("/${API}/${V1}/${PROFESSIONS}")
    suspend fun getPartList(
    ): BaseResponseNullable<PartListResDto>

    // 1.2 최초 로그인 시 회원가입
    @Multipart
    @POST("/${API}/${V1}/${SIGNUP}")
    suspend fun postSignUp(
        @Part image: MultipartBody.Part,
        @Part("nickname") nickname: RequestBody,
        @Part("region") region: RequestBody,
        @Part("profession") profession: RequestBody,
    ): BaseResponseNullable<Unit>

}