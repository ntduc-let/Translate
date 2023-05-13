package com.ntduc.baseproject.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ntduc.baseproject.data.dto.file.BaseFile

@Dao
interface BaseFileDao {

    @Insert
    fun insert(baseFile: BaseFile)

    @Update
    fun update(photo: BaseFile)

    @Delete
    fun delete(baseFile: BaseFile)

    @Query("DELETE FROM BaseFile")
    fun deleteAll()

    @Query("SELECT * FROM BaseFile")
    fun loadAll(): MutableList<BaseFile>

    @Query("SELECT * FROM BaseFile where id = :baseFileID")
    fun getBaseFileById(baseFileID: Long): BaseFile?
}