package com.ntduc.baseproject.data.dto.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseFile(
    open var id: Long? = null,
    open var title: String? = null,
    open var displayName: String? = null,
    open var mimeType: String? = null,
    open var size: Long? = null,
    open var dateAdded: Long? = null,
    open var dateModified: Long? = null,
    open var data: String? = null
) : Parcelable