package com.healios.dreams.data;

import androidx.lifecycle.MutableLiveData;

import com.healios.dreams.R;
import com.healios.dreams.model.PermissionModel;
import com.healios.dreams.util.application.DreaMSApplication;
import com.healios.dreams.util.managers.PermissionsManager;

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
        //TODO: retrieve list of permissions
        setFakeData();

        MutableLiveData<List<PermissionModel>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setFakeData() {
        dataSet = new ArrayList<>();
        dataSet.add(new PermissionModel(DreaMSApplication.appContext().getString(R.string.permissions_google_fit),false));
        dataSet.add(new PermissionModel(DreaMSApplication.appContext().getString(R.string.permissions_peak), PermissionsManager.isPeakAppInstalled()));
        dataSet.add(new PermissionModel(DreaMSApplication.appContext().getString(R.string.permissions_location),PermissionsManager.isAccesFineLocationPermissionGranted()));
        dataSet.add(new PermissionModel(DreaMSApplication.appContext().getString(R.string.permissions_activity_recognition),PermissionsManager.isActivityRecognitionPermissionGranted()));
        dataSet.add(new PermissionModel(DreaMSApplication.appContext().getString(R.string.permissions_external_sensors),PermissionsManager.isBodySensorPermissionGranted()));

    }

}
