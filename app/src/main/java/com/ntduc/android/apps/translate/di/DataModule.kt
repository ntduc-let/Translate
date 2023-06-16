
package com.ntduc.android.apps.translate.di

import com.ntduc.android.apps.translate.data.DataRepository
import com.ntduc.android.apps.translate.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource
}
