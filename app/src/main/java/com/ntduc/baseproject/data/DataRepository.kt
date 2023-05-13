package com.ntduc.baseproject.data

import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.data.dto.frames.DataFrames
import com.ntduc.baseproject.data.local.LocalData
import com.ntduc.baseproject.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun requestFrames(): Flow<Resource<DataFrames>> {
        return flow {
            emit(remoteRepository.requestFrames())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestAllFiles(types: List<String>): Flow<Resource<List<BaseFile>>> {
        return flow {
            emit(localRepository.requestAllFiles(types))
        }.flowOn(ioDispatcher)
    }
}
