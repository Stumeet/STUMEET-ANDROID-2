package com.aramtory.stumeet.data.api.my

import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.enums.ReviewSortType
import com.aramtory.stumeet.data.dto.req.my.ReviewReqDto
import com.aramtory.stumeet.data.dto.res.my.MyResDto
import com.aramtory.stumeet.data.dto.res.my.ReviewListResDto
import com.aramtory.stumeet.data.dto.res.my.ReviewStaticListResDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiService {
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val MEMBERS = "members"
        const val ME = "me"
        const val REVIEWS = "reviews"
        const val TAGS = "tags"
        const val STATS = "stats"
        const val STUDIES = "studies"
        const val LEGACY = "legacy"
        const val HIDE = "hide"
        const val LOGOUT = "logout"
    }

    /**사용자 정보 처리**/
    // 1.6 내 정보 조회
    @GET("/${API}/${V1}/${MEMBERS}/${ME}")
    suspend fun getMyInfo(
    ): BaseResponseNullable<MyResDto>

    // 1.7 사용자 정보 수정
    @PATCH("/${API}/${V1}/${MEMBERS}/${ME}")
    suspend fun patchMyInfo(
        @Query("nickname") nickname: Int,
        @Query("region") region: Int,
        @Query("profession") profession: Int,
    ): BaseResponseNullable<Unit>

    // 6.2 멤버 리뷰 조회
    @GET("/${API}/${V1}/${REVIEWS}")
    suspend fun getReviewList(
        @Query("size") size: Int,
        @Query("page") page: Int,
        @Query("sort") sort: ReviewSortType,
    ): BaseResponseNullable<ReviewListResDto>

    // 6.3 멤버 리뷰 태그 통계 조회
    @GET("/${API}/${V1}/${REVIEWS}/${TAGS}/${STATS}")
    suspend fun getStaticReviewList(
    ): BaseResponseNullable<ReviewStaticListResDto>

    /**리뷰 및 스터디 처리**/
    // 6.1 리뷰 등록
    @POST("/${API}/${V1}/${REVIEWS}")
    suspend fun postReview(
        @Body reviewReqDto: ReviewReqDto,
    ): BaseResponseNullable<MyResDto>

    // 4.9 레거시 스터디 숨김 처리
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}/${LEGACY}/${HIDE}")
    suspend fun patchStudyLegacy(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<Unit>

    /**로그아웃**/
    // 1.2 로그아웃
    @POST("/$API/$V1/$LOGOUT")
    suspend fun postLogout(
    ): BaseResponseNullable<Unit>

}