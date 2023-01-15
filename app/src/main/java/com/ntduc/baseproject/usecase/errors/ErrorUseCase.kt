package com.ntduc.baseproject.usecase.errors

import com.ntduc.baseproject.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
