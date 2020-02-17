package com.healios.dreams.util.managers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.healios.dreams.util.application.DreaMSApplication;

public class PermissionsManager {

    private static String TAG = PermissionsManager.class.getSimpleName();

    // Permission List
    public static final int PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE = 1;
    public static final int PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE = 2;
    public static final int PERMISSION_BODY_SENSORS_REQUEST_CODE = 3;
    public static final int PERMISSION_CAMERA_REQUEST_CODE = 4;
    public static final int PERMISSION_RECORD_AUDIO_REQUEST_CODE = 5;

    // Third Party App List
    private static final String THIRD_PARTY_APP_PEAK = "com.brainbow.peak.app";
    private static final String THIRD_PARTY_APP_GOOGLE_FIT = "com.google.android.apps.fitness";

    //region: Permission checker
    public static boolean isActivityRecognitionPermissionGranted() {
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition()) {
            return isPermissionGranted(Manifest.permission.ACTIVITY_RECOGNITION);
        }
        return false;
    }

    public static boolean isAccesFineLocationPermissionGranted() {
        return isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static boolean isBodySensorPermissionGranted() {
        return isPermissionGranted(Manifest.permission.BODY_SENSORS);
    }

    public static boolean isCameraPermissionGranted() {
        return isPermissionGranted(Manifest.permission.CAMERA);
    }

    public static boolean isRecordAudioPermissionGranted() {
        return isPermissionGranted(Manifest.permission.RECORD_AUDIO);
    }
    //endregion

    //region: Permission request
    public static void askForActivityRecognitionPermission(Activity activity) {
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition()) {
            askForPermission(activity, Manifest.permission.ACTIVITY_RECOGNITION, PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE);
        } else {
            Log.e(TAG, String.format("The device (%d) does not support Activity recognition (%d)", Build.VERSION.SDK_INT, Build.VERSION_CODES.Q));
        }
    }

    public static void askForActivityRecognitionPermission(Fragment fragment) {
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition()) {
            askForPermission(fragment, Manifest.permission.ACTIVITY_RECOGNITION, PERMISSION_ACTIVITY_RECOGNITION_REQUEST_CODE);
        } else {
            Log.e(TAG, String.format("The device (%d) does not support Activity recognition (%d)", Build.VERSION.SDK_INT, Build.VERSION_CODES.Q));
        }
    }

    public static void askForLocationPermission(Activity activity) {
        askForPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE);
    }

    public static void askForLocationPermission(Fragment fragment) {
        askForPermission(fragment, Manifest.permission.ACCESS_FINE_LOCATION, PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE);
    }

    public static void askForBodySensorPermission(Activity activity) {
        askForPermission(activity, Manifest.permission.BODY_SENSORS, PERMISSION_BODY_SENSORS_REQUEST_CODE);
    }

    public static void askForBodySensorPermission(Fragment fragment) {
        askForPermission(fragment, Manifest.permission.BODY_SENSORS, PERMISSION_BODY_SENSORS_REQUEST_CODE);
    }

    public static void askForCameraPermission(Activity activity) {
        askForPermission(activity, Manifest.permission.CAMERA, PERMISSION_CAMERA_REQUEST_CODE);
    }

    public static void askForCameraPermission(Fragment fragment) {
        askForPermission(fragment, Manifest.permission.CAMERA, PERMISSION_CAMERA_REQUEST_CODE);
    }

    public static void askForRecordAudioPermission(Activity activity) {
        askForPermission(activity, Manifest.permission.RECORD_AUDIO, PERMISSION_RECORD_AUDIO_REQUEST_CODE);
    }

    public static void askForRecordAudioPermission(Fragment fragment) {
        askForPermission(fragment, Manifest.permission.RECORD_AUDIO, PERMISSION_RECORD_AUDIO_REQUEST_CODE);
    }
    //endregion

    //region: Third party Apps installed
    public static boolean isPeakAppInstalled() {
        return isThirdPartyAppInstalled(THIRD_PARTY_APP_PEAK);
    }

    public static boolean isGoogleFitAppInstalled() {
        return isThirdPartyAppInstalled(THIRD_PARTY_APP_GOOGLE_FIT);
    }
    //endregion

    //region: General utils
    private static Boolean isPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(DreaMSApplication.appContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static Boolean isThirdPartyAppInstalled(String appPackageName) {
        try {
            DreaMSApplication.appContext().getPackageManager().getPackageInfo(appPackageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, String.format("Package '%s' not found", appPackageName));
        }
        return false;
    }

    private static void askForPermission(Activity activity, String permission, int requestCode) {
        if (VersionCompatibilityManager.appIsCompatibleWithPermissionRequest()) {
            // Should we show an explanation?
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                activity.requestPermissions(new String[]{permission}, requestCode);
            }
        }

        /*
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
         */
    }

    private static void askForPermission(Fragment fragment, String permission, int requestCode) {
        /*
        // Should we show an explanation?
        if (fragment.shouldShowRequestPermissionRationale(permission)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {
            // No explanation needed; request the permission
            fragment.requestPermissions(new String[]{permission}, requestCode);
        }
         */
        fragment.requestPermissions(new String[]{permission}, requestCode);
    }


    //endregion
}
