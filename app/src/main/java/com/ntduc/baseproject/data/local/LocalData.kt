package com.ntduc.baseproject.data.local

import android.content.Context
import com.ntduc.baseproject.data.Resource
import com.ntduc.baseproject.data.dto.base.BaseFile
import com.ntduc.baseproject.utils.file.getFiles
import javax.inject.Inject

/**
 * Created by TruyenIT
 */

class LocalData @Inject constructor(val context: Context) {
    fun requestAllFiles(types: List<String>): Resource<List<BaseFile>> {
        val baseFileList = context.getFiles(types = types)
        return Resource.Success(baseFileList)
    }
}

