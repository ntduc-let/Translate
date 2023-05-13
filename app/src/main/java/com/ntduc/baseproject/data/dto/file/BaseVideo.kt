package com.ntduc.baseproject.data.dto.file

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseVideo(
    override var id: Long? = null,
    override var title: String? = null,
    override var displayName: String? = null,
    override var mimeType: String? = null,
    override var size: Long? = null,
    override var dateAdded: Long? = null,
    override var dateModified: Long? = null,
    override var data: String? = null,
    open var height: Long? = null,
    open var width: Long? = null,
    open var album: String? = null,
    open var artist: String? = null,
    open var duration: Long? = null,
    open var bucketID: Long? = null,
    open var bucketDisplayName: String? = null,
    open var resolution: String? = null
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