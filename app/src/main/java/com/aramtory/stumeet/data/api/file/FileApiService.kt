package com.aramtory.stumeet.data.api.file

import com.aramtory.stumeet.data.api.ApiKeyStorage.API
import com.aramtory.stumeet.data.api.ApiKeyStorage.PRESIGNED_URLS
import com.aramtory.stumeet.data.api.ApiKeyStorage.V1
import com.aramtory.stumeet.data.dto.common.BaseResponseNullable
import com.aramtory.stumeet.data.dto.req.file.FileReqDto
import com.aramtory.stumeet.data.dto.res.file.FileResDto
import retrofit2.http.Body
import retrofit2.http.POST

interface FileApiService {
    @POST("/$API/$V1/$PRESIGNED_URLS")
    suspend fun postPresignedUrl(
        @Body request: FileReqDto,
    ): BaseResponseNullable<FileResDto>
}