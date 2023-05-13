package com.ntduc.baseproject.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.FileProvider
import com.ntduc.baseproject.constant.FileType
import com.ntduc.baseproject.data.dto.file.BaseApk
import com.ntduc.baseproject.data.dto.app.BaseApp
import com.ntduc.baseproject.utils.file.getFiles
import java.io.File

/*
*  <uses-permission
*    android:name="android.permission.QUERY_ALL_PACKAGES"
*    tools:ignore="QueryAllPackagesPermission" />
 */
@SuppressLint("QueryPermissionsNeeded")
fun Context.getApps(
    isSystem: Boolean = false
): List<BaseApp> {
    val apps = ArrayList<BaseApp>()
    val applicationInfos = this.packageManager.getInstalledApplications(0)

    for (applicationInfo in applicationInfos) {
        if (isSystem || !isSystemApplication(applicationInfo)) {
            val newInfo = BaseApp()
            try {
                newInfo.name = applicationInfo.loadLabel(this.packageManager).toString()
            } catch (_: Exception) {
            }
            newInfo.packageName = applicationInfo.packageName
            try {
                newInfo.icon = applicationInfo.loadIcon(this.packageManager)
            } catch (_: Exception) {
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    newInfo.category =
                        ApplicationInfo.getCategoryTitle(this, applicationInfo.category).toString()
                } catch (_: Exception) {
                }
            }
            newInfo.dataDir = applicationInfo.dataDir
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                newInfo.minSdkVersion = applicationInfo.minSdkVersion
            }
            newInfo.targetSdkVersion = applicationInfo.targetSdkVersion
            newInfo.processName = applicationInfo.processName
            newInfo.nativeLibraryDir = applicationInfo.nativeLibraryDir
            newInfo.publicSourceDir = applicationInfo.publicSourceDir

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val storageStatsManager: StorageStatsManager = this.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
                try {
                    val storageStats: StorageStats = storageStatsManager.queryStatsForUid(applicationInfo.storageUuid, applicationInfo.uid)
                    val cacheSize = storageStats.cacheBytes
                    val dataSize = storageStats.dataBytes
                    val apkSize = storageStats.appBytes
                    newInfo.size = cacheSize + dataSize + apkSize
                } catch (_: Exception) {
                }
            } else {
                newInfo.size = null
            }

            val pi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                this.packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.PackageInfoFlags.of(0))
            } else {
                this.packageManager.getPackageInfo(applicationInfo.packageName, 0)
            }
            newInfo.firstInstallTime = pi.firstInstallTime
            newInfo.lastUpdateTime = pi.lastUpdateTime

            newInfo.sourceDir = applicationInfo.sourceDir
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                newInfo.splitNames = applicationInfo.splitNames
            }
            newInfo.splitPublicSourceDirs = applicationInfo.splitPublicSourceDirs
            newInfo.splitSourceDirs = applicationInfo.splitSourceDirs
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                newInfo.storageUuid = applicationInfo.storageUuid
            }
            newInfo.taskAffinity = applicationInfo.taskAffinity
            newInfo.uid = applicationInfo.uid
            apps.add(newInfo)
        }
    }
    return apps
}

fun Context.getApks(
    directoryPath: String = ""
): List<BaseApk> {
    val files = ArrayList<BaseApk>()

    val list = getFiles(directoryPath, listOf(*FileType.APK))
    list.forEach {
        val pi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.packageManager.getPackageArchiveInfo(it.data!!, PackageManager.PackageInfoFlags.of(0))
        } else {
            this.packageManager.getPackageArchiveInfo(it.data!!, 0)
        }
        val icon = if (pi != null) {
            pi.applicationInfo.sourceDir = it.data!!
            pi.applicationInfo.publicSourceDir = it.data!!

            pi.applicationInfo.loadIcon(this.packageManager)
        } else {
            null
        }

        files.add(BaseApk(it.id, it.title, it.displayName, it.mimeType, it.size, it.dateAdded, it.dateModified, it.data, icon))
    }
    return files
}

fun Activity.openApp(packageName: String) {
    var intent: Intent? = packageManager.getLaunchIntentForPackage(packageName)
    if (intent == null) {
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=$packageName")
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivityForResult(intent, REQUEST_CODE_OPEN_APP)
}

fun Activity.openSettingApp(packageName: String) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.fromParts("package", packageName, null)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivityForResult(intent, REQUEST_CODE_OPEN_APP)
}

/*
* <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES" />
 */
fun Activity.uninstallApp(packageName: String) {
    val intent = Intent(
        Intent.ACTION_DELETE,
        Uri.fromParts("package", packageName, null)
    )
    startActivityForResult(intent, REQUEST_CODE_UNINSTALL_APP)
}

/*
* <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
 */
fun Activity.installApk(path: String, authority: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val uri = FileProvider.getUriForFile(this, authority, File(path))
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        startActivityForResult(intent, REQUEST_CODE_INSTALL_APP)
    } else {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(File(path)), "application/vnd.android.package-archive")
        startActivityForResult(intent, REQUEST_CODE_INSTALL_APP)
    }
}

private fun isSystemApplication(appInfo: ApplicationInfo): Boolean {
    return appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
}

const val REQUEST_CODE_OPEN_APP = 100
const val REQUEST_CODE_SETTING_APP = 200
const val REQUEST_CODE_INSTALL_APP = 300
const val REQUEST_CODE_UNINSTALL_APP = 400