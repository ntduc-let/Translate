package com.ntduc.android.apps.translate

import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}

