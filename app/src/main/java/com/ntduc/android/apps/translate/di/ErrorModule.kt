package com.ntduc.android.apps.translate.di

import com.ntduc.android.apps.translate.data.error.mapper.ErrorMapper
import com.ntduc.android.apps.translate.data.error.mapper.ErrorMapperSource
import com.ntduc.android.apps.translate.data.error.ErrorManager
import com.ntduc.android.apps.translate.data.error.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
