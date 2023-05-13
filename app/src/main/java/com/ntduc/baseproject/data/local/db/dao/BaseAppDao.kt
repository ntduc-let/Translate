package com.ntduc.baseproject.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ntduc.baseproject.data.dto.app.BaseApp
import com.ntduc.baseproject.data.dto.file.BaseFile

@Dao
interface BaseAppDao {

    @Insert
    fun insert(baseApp: BaseApp)

    @Update
    fun update(baseApp: BaseApp)

    @Delete
    fun delete(baseApp: BaseApp)

    @Query("DELETE FROM BaseApp")
    fun deleteAll()

    @Query("SELECT * FROM BaseApp")
    fun getAll(): MutableList<BaseApp>

    @Query("SELECT * FROM BaseApp where packageName = :packageName")
    fun getByPackageName(packageName: String): BaseApp?
}