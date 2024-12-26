package com.aramtory.stumeet.data.api.file

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Url

interface S3ApiService {
    @PUT
    suspend fun putImgToS3(
        @Url preSignedUrl: String,
        @Body image: RequestBody,
    ): Response<Void>

}