package com.healios.dreams.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class PersonalInformationViewModel : ViewModel() {

    val avatar = MutableLiveData<Int>()
    val nickname = MutableLiveData<String>()



}