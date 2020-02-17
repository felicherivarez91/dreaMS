package com.healios.dreams.util.managers;

import android.os.Build;

public class VersionCompatibilityManager {


    public static boolean appIsCompatibleWithPermissionRequest() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    public static boolean appIsCompatibleWithActivityRecognition() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q);
    }
}
