package com.healios.dreams.ui.permissions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.ViewModel;

import com.healios.dreams.data.PermissionRepository;
import com.healios.dreams.model.PermissionModel;

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


}
