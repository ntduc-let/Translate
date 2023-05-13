package com.ntduc.baseproject.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.data.local.db.dao.BaseFileDao

@Database(entities = [BaseFile::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val baseFileDao: BaseFileDao

    companion object {
        const val DB_NAME = "BaseFileDatabase.db"
    }
}