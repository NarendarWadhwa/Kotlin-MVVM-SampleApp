package com.example.findme.base

import android.app.Application
import com.facebook.stetho.Stetho

class FindMeApp : Application() {

    companion object {
        lateinit var instance: FindMeApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }
}

