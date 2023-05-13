package com.ntduc.baseproject.data.dto.app

import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.UUID

@Parcelize
@Entity(tableName = "BaseApp")
open class BaseApp(
    open var name: String? = null,
    @PrimaryKey open var packageName: String? = null,
    open var icon: @RawValue Drawable? = null,
    open var category: String? = null,
    open var dataDir: String? = null,
    open var minSdkVersion: Int? = null,
    open var targetSdkVersion: Int? = null,
    open var nativeLibraryDir: String? = null,
    open var processName: String? = null,
    open var publicSourceDir: String? = null,
    open var size: Long? = null,
    open var firstInstallTime: Long? = null,
    open var lastUpdateTime: Long? = null,
    open var sourceDir: String? = null,
    open var splitNames: Array<String>? = null,
    open var splitPublicSourceDirs: Array<String>? = null,
    open var splitSourceDirs: Array<String>? = null,
    open var storageUuid: UUID? = null,
    open var taskAffinity: String? = null,
    open var uid: Int? = null
) : Parcelable