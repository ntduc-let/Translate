package com.ntduc.baseproject.data.error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
