package com.example.compsoetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.Settings
import android.view.Surface

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Settings.System.putInt(this.contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_90)
    }

    override fun onCreate() {
        super.onCreate()
        Settings.System.putInt(this.contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_90)

        stopSelf()
    }
}