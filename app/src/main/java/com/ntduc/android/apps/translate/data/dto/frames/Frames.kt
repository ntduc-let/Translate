package com.ntduc.android.apps.translate.data.dto.frames

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Frames(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "name_vi")
    val name_vi: String = "",
    @Json(name = "folder")
    val folder: String = "",
    @Json(name = "icon")
    val icon: String = "",
    @Json(name = "cover")
    val cover: String = "",
    @Json(name = "totalImage")
    val totalImage: Int = 0,
    @Json(name = "lock")
    val lock: Boolean = false,
    @Json(name = "openPackageName")
    val openPackageName: String = "",
    @Json(name = "defines")
    val defines: List<DefinesFrames> = listOf(),
) : Parcelable