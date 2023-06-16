package com.ntduc.android.apps.translate.data.remote

import com.ntduc.android.apps.translate.data.Resource
import com.ntduc.android.apps.translate.data.dto.frames.DataFrames

internal interface RemoteDataSource {
    suspend fun requestFrames(): Resource<DataFrames>
}