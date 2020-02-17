package com.healios.dreams

import android.app.Application


class DreaMSApp: Application() {

    companion object {
        lateinit var instance: DreaMSApp
            private set
    }

}