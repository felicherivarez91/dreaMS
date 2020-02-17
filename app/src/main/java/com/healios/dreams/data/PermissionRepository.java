package com.healios.dreams.data;

import androidx.lifecycle.MutableLiveData;

import com.healios.dreams.DreaMSApp;
import com.healios.dreams.R;
import com.healios.dreams.model.PermissionModel;
import com.healios.dreams.util.managers.PermissionsManager;
import com.healios.dreams.util.managers.VersionCompatibilityManager;

import java.util.ArrayList;
import java.util.List;

public class PermissionRepository {

    private static PermissionRepository instance;

    private List<PermissionModel> dataSet;

    public static PermissionRepository getInstance() {
        if (instance == null) {
            instance = new PermissionRepository();
        }
        return instance;
    }

    public MutableLiveData<List<PermissionModel>> getPermissions() {
        setRequiredPermissionsToCompleteLogin();

        MutableLiveData<List<PermissionModel>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setRequiredPermissionsToCompleteLogin() {
        dataSet = new ArrayList<>();
        //FIXME: Change Google Fit enabled to a non hardcoded value
        dataSet.add(new PermissionModel(DreaMSApp.getInstance().getString(R.string.permissions_google_fit),true));
        dataSet.add(new PermissionModel(DreaMSApp.getInstance().getString(R.string.permissions_peak), PermissionsManager.isPeakAppInstalled()));
        dataSet.add(new PermissionModel(DreaMSApp.getInstance().getString(R.string.permissions_location),PermissionsManager.isAccesFineLocationPermissionGranted()));
        dataSet.add(new PermissionModel(DreaMSApp.getInstance().getString(R.string.permissions_external_sensors),PermissionsManager.isBodySensorPermissionGranted()));
        if (VersionCompatibilityManager.appIsCompatibleWithActivityRecognition())
            dataSet.add(new PermissionModel(DreaMSApp.getInstance().getString(R.string.permissions_activity_recognition),PermissionsManager.isActivityRecognitionPermissionGranted()));
    }

}
