package com.healios.dreams.ui.permissions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.healios.dreams.data.PermissionRepository
import com.healios.dreams.model.PermissionModel
import com.healios.dreams.util.EmptyLiveEvent
import com.healios.dreams.util.EmptyMutableLiveEvent
import com.healios.dreams.util.Event
import com.healios.dreams.util.managers.PermissionsManager
import com.healios.dreams.util.managers.VersionCompatibilityManager

class PermissionViewModel : ViewModel() {

    private val TAG: String = "PermissionViewModel"
    private var currentPermissionPosition: Int = 0

    private var _permissionList = MutableLiveData<List<PermissionModel>>()
    var permissionList:LiveData<List<PermissionModel>> = _permissionList

    private val mPermissionRepository: PermissionRepository = PermissionRepository.getInstance()
    var canContinue = MutableLiveData(false)

    private val _startEvent = EmptyMutableLiveEvent()
    val startEvent: EmptyLiveEvent = _startEvent
    init {
        _permissionList.value = mPermissionRepository.permissions
        manageGetStartedButtonStatus()
    }

    fun updatePermissionStatus(position: Int, isEnabled: Boolean) {
        val permissionModelList = _permissionList.value!!
        permissionModelList[position].enabled = isEnabled
        _permissionList.value = permissionModelList
        manageGetStartedButtonStatus()
    }

    fun start() {
        _startEvent.value = Event(Unit)
    }

    private fun manageGetStartedButtonStatus() {
        for (permission in _permissionList.value!!) {
            if (!permission.enabled) {
                canContinue.postValue(false)
                return
            }
        }
        canContinue.postValue(true)
    }


}