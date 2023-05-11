package com.ntduc.baseproject.data.remote

import com.ntduc.baseproject.data.Resource
import com.ntduc.baseproject.data.dto.frames.DataFrames

internal interface RemoteDataSource {
    suspend fun requestFrames(): Resource<DataFrames>
}