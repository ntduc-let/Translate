package com.ntduc.baseproject.data.dto.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MyPage(
    @Json(name = "size")
    val size: Int? = null,
    @Json(name = "number")
    val number: Int? = null,
    @Json(name = "totalElements")
    val totalElements: Int? = null,
    @Json(name = "totalPages")
    val totalPages: Int? = null
) : Parcelable