package com.healios.dreams.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.data.AvatarRepository
import com.healios.dreams.model.AvatarModel

class SelectAvatarViewModel : ViewModel() {

    private val _avatarList = MutableLiveData<List<AvatarModel>>()
    val avatarList: LiveData<List<AvatarModel>> = _avatarList

    var canSaveAvatar = MutableLiveData<Boolean>(false)

    //region: Init viewmodel
    init {

        val list = AvatarRepository.getInstance().avatarList
        _avatarList.value= list
        _avatarList.postValue(list)
    }
    //endregion


    fun onItemClick(position: Int, avatar: AvatarModel) {
        //Refresh avatar list
        val iterator = avatarList.value?.listIterator()
        val updatedAvatars = ArrayList<AvatarModel>()
        iterator?.forEach {
            it.isSelected = (it == avatar)
            updatedAvatars.add(it)
        }

        _avatarList.postValue(updatedAvatars)
    }
}
