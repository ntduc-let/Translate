package com.ntduc.android.apps.translate.data.error

import com.ntduc.android.apps.translate.data.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
