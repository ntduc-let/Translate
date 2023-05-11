package com.ntduc.baseproject.data.remote.service

import com.ntduc.baseproject.data.dto.frames.DataFrames
import retrofit2.Response
import retrofit2.http.GET

interface FramesService {
    @GET("frames.json")
    suspend fun fetchFrames(): Response<DataFrames>
}
