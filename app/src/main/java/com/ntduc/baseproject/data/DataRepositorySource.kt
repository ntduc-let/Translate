package com.ntduc.baseproject.data

import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.data.dto.frames.DataFrames
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestFrames(): Flow<Resource<DataFrames>>
    suspend fun requestAllFiles(types: List<String>): Flow<Resource<List<BaseFile>>>
}
