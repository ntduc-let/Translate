package com.ntduc.android.apps.translate.data

import com.ntduc.android.apps.translate.data.dto.file.BaseFile
import com.ntduc.android.apps.translate.data.dto.frames.DataFrames
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestFrames(): Flow<Resource<DataFrames>>
    suspend fun requestAllFiles(types: List<String>): Flow<Resource<List<BaseFile>>>
}
