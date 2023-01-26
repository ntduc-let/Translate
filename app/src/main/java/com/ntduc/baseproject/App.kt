package com.ntduc.baseproject

import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by TruyenIT
 */
@HiltAndroidApp
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}

