package com.cxz.asmsample

import android.app.Application
import com.cxz.auto_track_sdk.SensorsDataAPI

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SensorsDataAPI.init(this)

    }

}