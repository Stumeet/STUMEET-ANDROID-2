package com.aramtory.stumeet.data.api.study

import com.aramtory.stumeet.data.api.ApiKeyStorage.ACTIVITIES
import com.aramtory.stumeet.data.api.ApiKeyStorage.API
import com.aramtory.stumeet.data.api.ApiKeyStorage.BRIEF
import com.aramtory.stumeet.data.api.ApiKeyStorage.DETAIL
import com.aramtory.stumeet.data.api.ApiKeyStorage.MEMBER
import com.aramtory.stumeet.data.api.ApiKeyStorage.STUDIES
import com.aramtory.stumeet.data.api.ApiKeyStorage.V1
import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.req.study.study_activity.StudyActivityReqDto
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityBriefListResDto
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityDetailListResDto
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityMemberListResDto
import com.aramtory.stumeet.data.dto.res.study.study_activity.StudyActivityResDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StudyActivityApiService {

    /** 활동 CRUD **/
    // 5.1 활동 생성
    @POST("/${API}/${V1}/${STUDIES}/{studyId}/${ACTIVITIES}")
    suspend fun postStudyActivity(
        @Path(value = "studyId") studyId: Int,
        @Body studyActivityInfo: StudyActivityReqDto,
    ): BaseResponseNullable<StudyActivityResDto>

    // 5.2 활동 수정
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}/${ACTIVITIES}/{activityId}")
    suspend fun patchStudyActivity(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "activityId") activityId: Int,
        @Body studyActivityInfo: StudyActivityReqDto,
    ): BaseResponseNullable<StudyActivityResDto>

    // 5.8 활동 삭제
    @DELETE("/${API}/${V1}/${STUDIES}/{studyId}/${ACTIVITIES}/{activityId}")
    suspend fun deleteStudyActivity(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "activityId") activityId: Int,
    ): BaseResponseNullable<Unit>

    // 5.5 활동 단일 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${ACTIVITIES}/{activityId}")
    suspend fun getStudyActivity(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "activityId") activityId: Int,
    ): BaseResponseNullable<StudyActivityResDto>

    // 5.6 스터디 활동에 참여중인 멤버 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${ACTIVITIES}/{activityId}/${MEMBER}")
    suspend fun getStudyActivityAttendMember(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "activityId") activityId: Int,
    ): BaseResponseNullable<StudyActivityMemberListResDto>


    /** 활동 리스트 조회 **/
    // 5.3 활동 상세 목록 조회
    @GET("/${API}/${V1}/${STUDIES}/${ACTIVITIES}/${DETAIL}")
    suspend fun getStudyActivityDetail(
        @Query("size") size: Int? = null,
        @Query("page") page: Int? = null,
        @Query("isNotice") isNotice: Boolean? = null,
        @Query("studyId") studyId: Int? = null,
        @Query("category") category: String? = null,
    ): BaseResponseNullable<StudyActivityDetailListResDto>

    // 5.4 활동 간략 목록 조회
    @GET("/${API}/${V1}/${STUDIES}/${ACTIVITIES}/${BRIEF}")
    suspend fun getStudyActivityBrief(
        @Query("size") size: Int? = null,
        @Query("page") page: Int? = null,
        @Query("isNotice") isNotice: Boolean? = null,
        @Query("studyId") studyId: Int? = null,
        @Query("memberId") memberId: Int? = null,
        @Query("category") category: String? = null,
        @Query("fromDate") fromDate: String? = null,
        @Query("toDate") toDate: String? = null
    ): BaseResponseNullable<StudyActivityBriefListResDto>
}