package com.ntduc.baseproject.data.dto.base

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
open class BaseApk(
    override var id: Long? = null,
    override var title: String? = null,
    override var displayName: String? = null,
    override var mimeType: String? = null,
    override var size: Long? = null,
    override var dateAdded: Long? = null,
    override var dateModified: Long? = null,
    override var data: String? = null,
    open var icon: @RawValue Drawable? = null
) : BaseFile(
    id,
    title,
    displayName,
    mimeType,
    size,
    dateAdded,
    dateModified,
    data
), Parcelable