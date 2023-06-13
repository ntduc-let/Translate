package com.ntduc.baseproject.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import com.ntduc.baseproject.BuildConfig
import com.ntduc.baseproject.R
import com.ntduc.baseproject.utils.file.readToString
import com.ntduc.baseproject.utils.file.writeToFile
import java.io.File

object Logx {
    private val PERMISSIONS_REQUIRED = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun d(context: Context, tag: String, msg: String) {
        Log.d(tag, msg)

        if (BuildConfig.DEBUG) {
            val nameFile = "${context.getString(R.string.app_name)}_debug.txt"
            val fileLog = if (hasPermissions(context)) {
                File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$nameFile")
            } else {
                File("${context.filesDir.path}/$nameFile")
            }
            if (!fileLog.exists()) {
                fileLog.createNewFile()
            }

            try {
                val currentLog = fileLog.readToString()

                val log = "${System.currentTimeMillis()} - $tag: $msg"
                fileLog.writeToFile(currentLog + log + "\n")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun e(context: Context, tag: String, msg: String) {
        Log.e(tag, msg)

        if (BuildConfig.DEBUG) {
            val nameFile = "${context.getString(R.string.app_name)}_error.txt"
            val fileLog = if (hasPermissions(context)) {
                File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$nameFile")
            } else {
                File("${context.filesDir.path}/$nameFile")
            }
            if (!fileLog.exists()) {
                fileLog.createNewFile()
            }

            try {
                val currentLog = fileLog.readToString()

                val log = "${System.currentTimeMillis()} - $tag: $msg"
                fileLog.writeToFile(currentLog + log + "\n")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun i(context: Context, tag: String, msg: String) {
        Log.i(tag, msg)

        if (BuildConfig.DEBUG) {
            val nameFile = "${context.getString(R.string.app_name)}_info.txt"
            val fileLog = if (hasPermissions(context)) {
                File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$nameFile")
            } else {
                File("${context.filesDir.path}/$nameFile")
            }
            if (!fileLog.exists()) {
                fileLog.createNewFile()
            }

            try {
                val currentLog = fileLog.readToString()

                val log = "${System.currentTimeMillis()} - $tag: $msg"
                fileLog.writeToFile(currentLog + log + "\n")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun w(context: Context, tag: String, msg: String) {
        Log.w(tag, msg)

        if (BuildConfig.DEBUG) {
            val nameFile = "${context.getString(R.string.app_name)}_warn.txt"
            val fileLog = if (hasPermissions(context)) {
                File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$nameFile")
            } else {
                File("${context.filesDir.path}/$nameFile")
            }
            if (!fileLog.exists()) {
                fileLog.createNewFile()
            }

            try {
                val currentLog = fileLog.readToString()

                val log = "${System.currentTimeMillis()} - $tag: $msg"
                fileLog.writeToFile(currentLog + log + "\n")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hasPermissions(context: Context): Boolean {
        for (permission in PERMISSIONS_REQUIRED) {
            if (ContextCompat.checkSelfPermission(context, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}