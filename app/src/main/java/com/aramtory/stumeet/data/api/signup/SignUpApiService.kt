package com.aramtory.stumeet.data.api.signup

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
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val AUTH = "oauth"
        const val AUTHORIZATION = "Authorization"
        const val OAUTH_PROVIDE = "X-OAUTH-PROVIDER"
        const val KAKAO = "kakao"
        const val MEMBERS = "members"
        const val VALIDATE_NICKNAME = "validate-nickname"
        const val PROFESSIONS = "professions"
        const val SIGNUP = "signup"
        const val NOTIFICATION_TOKEN = "notification-token"
        const val RENEW = "renew"
    }

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