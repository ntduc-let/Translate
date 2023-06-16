package com.ntduc.android.apps.translate.di

import android.content.Context
import com.ntduc.android.apps.translate.data.local.LocalData
import com.ntduc.android.apps.translate.data.local.db.dao.BaseAppDao
import com.ntduc.android.apps.translate.data.local.db.dao.BaseFileDao
import com.ntduc.android.apps.translate.utils.network.Network
import com.ntduc.android.apps.translate.utils.network.NetworkConnectivity
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
    fun provideLocalRepository(@ApplicationContext context: Context, baseFileDao: BaseFileDao, baseAppDao: BaseAppDao): LocalData {
        return LocalData(context, baseFileDao, baseAppDao)
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
