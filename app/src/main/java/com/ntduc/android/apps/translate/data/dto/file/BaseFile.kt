package com.ntduc.android.apps.translate.data.dto.file

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "BaseFile")
open class BaseFile(
    @PrimaryKey open var id: Long? = null,
    open var title: String? = null,
    open var displayName: String? = null,
    open var mimeType: String? = null,
    open var size: Long? = null,
    open var dateAdded: Long? = null,
    open var dateModified: Long? = null,
    open var data: String? = null
) : Parcelable