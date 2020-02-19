package com.healios.dreams.ui.account

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectAvatarViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var canSaveAvatar = MutableLiveData<Boolean>(false)
    var avatarImageResource = MutableLiveData<Int>()



    //region: Init viewmodel
    init {
        //TODO: Initalize viewmodel



    }
    //endregion
}
