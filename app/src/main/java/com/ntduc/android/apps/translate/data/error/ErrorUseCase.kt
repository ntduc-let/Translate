package com.ntduc.android.apps.translate.data.error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
