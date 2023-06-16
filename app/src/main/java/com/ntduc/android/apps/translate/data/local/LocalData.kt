package com.ntduc.android.apps.translate.data.local

import android.content.Context
import com.ntduc.android.apps.translate.data.Resource
import com.ntduc.android.apps.translate.data.dto.file.BaseFile
import com.ntduc.android.apps.translate.data.local.db.dao.BaseAppDao
import com.ntduc.android.apps.translate.data.local.db.dao.BaseFileDao
import com.ntduc.android.apps.translate.utils.file.getFiles
import javax.inject.Inject

class LocalData @Inject constructor(private val context: Context, private val baseFileDao: BaseFileDao, private val baseAppDao: BaseAppDao) {
    fun requestAllFiles(types: List<String>): Resource<List<BaseFile>> {
        baseFileDao.deleteAll()
        val baseFileList = context.getFiles(types = types)
        baseFileList.forEach {
            baseFileDao.insert(it)
        }
        return Resource.Success(baseFileList)
    }
}

