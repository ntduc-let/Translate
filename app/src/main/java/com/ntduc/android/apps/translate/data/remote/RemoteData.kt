package com.ntduc.android.apps.translate.data.remote

import com.ntduc.android.apps.translate.data.Resource
import com.ntduc.android.apps.translate.data.dto.frames.DataFrames
import com.ntduc.android.apps.translate.data.error.NETWORK_ERROR
import com.ntduc.android.apps.translate.data.error.NO_INTERNET_CONNECTION
import com.ntduc.android.apps.translate.data.remote.service.FramesService
import com.ntduc.android.apps.translate.utils.network.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val framesService: FramesService,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {

    override suspend fun requestFrames(): Resource<DataFrames> {
        return when (val response = processCall(framesService::fetchFrames)) {
            is DataFrames -> {
                Resource.Success(data = response)
            }

            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
