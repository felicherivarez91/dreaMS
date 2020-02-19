package com.healios.dreams.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R

class PersonalInformationViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _isFormValid = MutableLiveData<Boolean>(true)
    val isFormatValid: LiveData<Boolean> = _isFormValid

    private val _errorText = MutableLiveData<String>("")
    val errorText: LiveData<String> = _errorText


    val canContinue = MediatorLiveData<Boolean>()
    var selectAvatarButtonText = MutableLiveData<String>()
    var avatarImageResource = MutableLiveData<Int>(R.drawable.ic_default_profile)



    //region: Init viewmodel
    init {
        //TODO: Initalize viewmodel
        canContinue.addSource(_isFormValid) {
            canContinue.value = !it && _isFormValid.value!!
        }


    }
    //endregion

    fun continueButtonPressed(nickname:String) {
        checkUniqueNickName(nickname)
    }

    fun onChangeAvatarPressed() {

    }

    fun onTextChanged(currentText: String) {

        val charactersOnly = checkAlphabeticCharactersOnly(currentText)

    }

    private fun checkAlphabeticCharactersOnly(currentText: String): Boolean {
        val alphabeticMask = Regex("[A-Za-z\\s]+")
        val matches = currentText.matches(alphabeticMask)
        if (!matches) {
            _errorText.postValue(DreaMSApp.instance.resources.getString(R.string.personalInformation_nicknameError))
        }
        _isFormValid.postValue(matches)

        return matches
    }

    private fun checkUniqueNickName(currentText: String) {
        TODO("Check with the system if nickname is unique") //To change body of created functions use File | Settings | File Templates.
    }

}
