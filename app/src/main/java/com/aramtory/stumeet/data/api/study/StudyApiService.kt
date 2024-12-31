package com.aramtory.stumeet.data.api.study

import com.aramtory.stumeet.data.api.ApiKeyStorage.API
import com.aramtory.stumeet.data.api.ApiKeyStorage.FINISH
import com.aramtory.stumeet.data.api.ApiKeyStorage.REPORT
import com.aramtory.stumeet.data.api.ApiKeyStorage.STUDIES
import com.aramtory.stumeet.data.api.ApiKeyStorage.V1
import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.common.enums.StudySortType
import com.aramtory.stumeet.data.dto.req.study.ReportReqDto
import com.aramtory.stumeet.data.dto.res.study.StudyDetailResDto
import com.aramtory.stumeet.data.dto.res.study.StudyListResDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface StudyApiService {
    /** 스터디 챕터 **/
    // 3.2 스터디 리스트 조회
    @GET("/${API}/${V1}/${STUDIES}")
    suspend fun getStudyActivityDetail(
        @Query("status") status: StudySortType
    ): BaseResponseNullable<StudyListResDto>

    // 3.3 스터디 생성
    @Multipart
    @POST("/${API}/${V1}/${STUDIES}")
    suspend fun postStudyCreate(
        @Part mainImageFile: MultipartBody.Part?,
        @Part("request") request: RequestBody
    ): BaseResponseNullable<Unit>

    // 3.6 스터디 활동 신고
    @POST("/${API}/${V1}/${REPORT}")
    suspend fun postReportCreate(
        @Body reportReqDto: ReportReqDto,
    ): BaseResponseNullable<Unit>

    /** 스터디 내부 **/
    // 3.1 스터디 상세 정보 조회
    @GET("/${API}/${V1}/${STUDIES}/{studyId}")
    suspend fun getStudyDetail(
        @Path(value = "studyId") studyId: Int,
    ): BaseResponseNullable<StudyDetailResDto>

    /** 스터디 슬라이드 바 **/
    // 3.4 스터디 수정
    @Multipart
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}")
    suspend fun patchStudyUpdate(
        @Path(value = "studyId") studyId: Int,
        @Part image: MultipartBody.Part,
        @Part("request") request: RequestBody
    ): BaseResponseNullable<Unit>

    // 3.5 스터디 완료
    @PATCH("/${API}/${V1}/${STUDIES}/{studyId}/${FINISH}")
    suspend fun patchStudyFinished(
        @Path(value = "studyId") studyId: Int
    ): BaseResponseNullable<Unit>

}