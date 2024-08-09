package com.vpd.vpdmobile

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VpdMobileApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}