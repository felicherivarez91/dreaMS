package com.healios.dreams.ui.relapse.affectedeyes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TapAffectedEyesViewModel : ViewModel() {

    var canContinue = MutableLiveData(false)
    var leftEye = MutableLiveData(false)
    var rightEye = MutableLiveData(false)

    fun clickTheLeftEyeImage(isEnabled: Boolean) {
        leftEye.postValue(isEnabled)
        canContinue.postValue(isEnabled)
    }

    fun clickTheRightEyeImage(isEnabled: Boolean) {
        rightEye.postValue(isEnabled)
        canContinue.postValue(isEnabled)
    }
}