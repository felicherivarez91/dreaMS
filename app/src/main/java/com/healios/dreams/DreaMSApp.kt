package com.healios.dreams

import android.app.Application


class DreaMSApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: DreaMSApp
            private set
    }

}