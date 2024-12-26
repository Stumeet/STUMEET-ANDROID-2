package com.aramtory.stumeet.data.api.file

import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.req.file.FileReqDto
import com.aramtory.stumeet.data.dto.res.file.FileResDto
import retrofit2.http.Body
import retrofit2.http.POST

interface FileApiService {
    companion object {
        const val V1 = "v1"
        const val API = "api"
        const val PRESIGNED_URLS = "presigned-urls"
    }

    @POST("/$API/$V1/$PRESIGNED_URLS")
    suspend fun postPresignedUrl(
        @Body request: FileReqDto,
    ): BaseResponseNullable<FileResDto>
}