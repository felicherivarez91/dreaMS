package com.healios.dreams.ui.permissions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.healios.dreams.data.PermissionRepository;
import com.healios.dreams.model.PermissionModel;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class PermissionViewModel extends ViewModel {

    private MutableLiveData<List<PermissionModel>> mPermissions;
    private PermissionRepository mPermissionRepository;

    public void init() {
        if (mPermissions != null){
            return;
        }
        mPermissionRepository = PermissionRepository.getInstance();
        mPermissions = mPermissionRepository.getPermissions();
    }


    public LiveData<List<PermissionModel>> getPermissions() {
        return mPermissions;
    }

    public void updatePermissionStatus(int position, boolean isEnabled){
       //TODO:
        List<PermissionModel> permissionModelList = mPermissions.getValue();
        permissionModelList.get(position).setEnabled(isEnabled);
        mPermissions.postValue(permissionModelList);

        /*
        ArrayList<PermissionModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new PermissionModel("DEMo",true));
        mPermissions.postValue(modelArrayList);
        */

    }

}
