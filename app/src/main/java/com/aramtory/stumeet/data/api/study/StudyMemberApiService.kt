package com.aramtory.stumeet.data.api.study

import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.res.study.study_member.CanSendGrapeResDto
import com.aramtory.stumeet.data.dto.res.study.study_member.IsAdminResDto
import com.aramtory.stumeet.data.dto.res.study.study_member.MemberListResDto
import com.aramtory.stumeet.data.dto.res.study.study_member.MemberResDto
import com.aramtory.stumeet.data.dto.res.study.study_member.MemberScreenResDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyMemberApiService {
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val STUDIES = "studies"
        const val MEMBER = "members"
        const val ME = "me"
        const val ADMIN = "admin"
        const val CHECK = "check"
        const val DELEGATE = "delegate"
        const val ACTIVITIES = "activities"
        const val STATUS = "status"
        const val GRAPES = "grapes"
        const val AVAILABLE = "available"
    }

    /** 멤버 기본 **/
    // 4.5 스터디 가입
    @POST("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}")
    suspend fun postStudyMemberCreate(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<Unit>

    // 4.2 스터디 멤버 목록 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}")
    suspend fun getStudyMemberList(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<MemberListResDto>

    // 4.1 스터디 멤버 단일 상세 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/{memberId}")
    suspend fun getStudyMemberDetail(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "memberId") memberId: Int,
    ): BaseResponseNullable<MemberResDto>

    /** 탈퇴 로직 **/
    // 4.3 스터디 멤버 탈퇴
    @DELETE("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/${ME}")
    suspend fun deleteMeInStudy(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<Unit>

    // 4.7 관리자 여부 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/${ME}/${ADMIN}/${CHECK}")
    suspend fun getMeIsAdmin(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<IsAdminResDto>

    // 4.6 관리자 위임
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/{memberId}/${ADMIN}/${DELEGATE}")
    suspend fun patchStudyAdmin(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "memberId") memberId: Int,
    ): BaseResponseNullable<Unit>

    /**멤버 관리 **/
    // 4.4 스터디 멤버 강퇴
    @DELETE("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/{memberId}")
    suspend fun deleteMemberInStudy(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "memberId") memberId: Int,
    ): BaseResponseNullable<Unit>

    // 5.7 스터디 활동 참여 멤버 상태 변경
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/{memberId}/${ACTIVITIES}/{activityId}/${STATUS}")
    suspend fun patchStudyAdmin(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "memberId") memberId: Int,
        @Path(value = "activityId") activityId: Int,
    ): BaseResponseNullable<Unit>

    // 4.8 포도알 전송 가능 여부 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${ME}/${GRAPES}/${AVAILABLE}")
    suspend fun getCanSendGrape(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<CanSendGrapeResDto>

    // 9.2 스터디 멤버 상세 화면 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}/${MEMBER}/{memberId}")
    suspend fun getMemberDetailScreen(
        @Path(value = "studyId") studyId: Int,
        @Path(value = "memberId") memberId: Int,
    ): BaseResponseNullable<MemberScreenResDto>
}