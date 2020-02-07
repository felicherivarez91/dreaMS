package com.healios.dreams.data;

import androidx.lifecycle.MutableLiveData;

import com.healios.dreams.model.PermissionModel;

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
        dataSet = new ArrayList<PermissionModel>();
        dataSet.add(new PermissionModel("Google Fit",false));
        dataSet.add(new PermissionModel("PEAK",false));
        dataSet.add(new PermissionModel("Location",false));
        dataSet.add(new PermissionModel("Activity Recognition",false));
        dataSet.add(new PermissionModel("External Sensors",false));

    }



}
