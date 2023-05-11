package com.ntduc.baseproject.data.dto.frames

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DefinesFrames(
    @Json(name = "start")
    val start: Int = 0,
    @Json(name = "end")
    val end: Int = 0,
    @Json(name = "totalCollageItemContainer")
    val totalCollageItemContainer: Int = 0,
    @Json(name = "indexDefineCollage")
    val indexDefineCollage: Int = 0,
) : Parcelable
