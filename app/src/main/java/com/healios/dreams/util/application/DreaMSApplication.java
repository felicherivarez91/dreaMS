package com.healios.dreams.util.application;

import android.app.Application;
import android.content.Context;

public class DreaMSApplication extends Application {

    private static DreaMSApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    private static DreaMSApplication getInstance() {
        return instance;
    }

    public static Context appContext() {
        return getInstance().getApplicationContext();
    }

}
