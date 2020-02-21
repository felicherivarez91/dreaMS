package com.healios.dreams.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.data.AvatarRepository
import com.healios.dreams.model.AvatarModel

class SelectAvatarViewModel : ViewModel() {

    private val _avatarList = MutableLiveData<List<AvatarModel>>()
    val avatarList: LiveData<List<AvatarModel>> = _avatarList

    private val _selectedAvatar = MutableLiveData<AvatarModel>()
    val selectedAvatar: LiveData<AvatarModel> = _selectedAvatar


    private val _savedAvatar = MutableLiveData<AvatarModel>()
    val savedAvatar: LiveData<AvatarModel> = _savedAvatar

    var canSaveAvatar = MutableLiveData<Boolean>(false)

    //region: Init viewmodel
    init {

        val list = AvatarRepository.getInstance().avatarList
        _avatarList.value= list
        canSaveAvatar.postValue( selectedAvatar.value != null)
    }
    //endregion


    fun onItemClick(position: Int, avatar: AvatarModel) {

        _selectedAvatar.postValue(avatar)

        canSaveAvatar.postValue( position + 1 > 0 )

        //Refresh avatar list
        val iterator = avatarList.value?.listIterator()
        val updatedAvatars = ArrayList<AvatarModel>()
        iterator?.forEach {
            it.isSelected = (it == avatar)
            updatedAvatars.add(it)
        }

        _avatarList.postValue(updatedAvatars)
    }

    fun onItemChanged(position: Int, avatar: AvatarModel) {

        canSaveAvatar.postValue( position + 1 > 0 )

        avatar.isSelected = true
        _selectedAvatar.postValue(avatar)

    }

    fun onSaveButtonPressed(){
        _savedAvatar.postValue(selectedAvatar.value)
    }
}
