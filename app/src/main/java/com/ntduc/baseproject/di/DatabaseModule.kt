package com.ntduc.baseproject.di

import android.app.Application
import androidx.room.Room
import com.ntduc.baseproject.data.local.db.AppDatabase
import com.ntduc.baseproject.data.local.db.dao.BaseFileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun providePhotoDao(appDatabase: AppDatabase): BaseFileDao {
        return appDatabase.baseFileDao
    }
}