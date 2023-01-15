package com.ntduc.baseproject.usecase.errors

import com.ntduc.baseproject.data.error.Error
import com.ntduc.baseproject.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by TruyenIT
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
