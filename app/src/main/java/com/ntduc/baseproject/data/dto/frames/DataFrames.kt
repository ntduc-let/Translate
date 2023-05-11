package com.ntduc.baseproject.data.dto.frames

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DataFrames(
    @Json(name = "start_link")
    val start_link: String = "",
    @Json(name = "listPhotoFrames")
    val listPhotoFrames: List<Frames> = listOf(),
) : Parcelable
