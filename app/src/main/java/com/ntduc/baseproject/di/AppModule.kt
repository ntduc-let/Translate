package com.ntduc.baseproject.di

import android.content.Context
import com.ntduc.baseproject.data.local.LocalData
import com.ntduc.baseproject.data.local.db.dao.BaseFileDao
import com.ntduc.baseproject.utils.network.Network
import com.ntduc.baseproject.utils.network.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context, dao: BaseFileDao): LocalData {
        return LocalData(context, dao)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }
}
