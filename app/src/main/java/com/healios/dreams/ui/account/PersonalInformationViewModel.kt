package com.healios.dreams.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R
import com.healios.dreams.data.AccountInfoProvider
import com.healios.dreams.data.AccountInformationManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.util.EmptyLiveEvent
import com.healios.dreams.util.EmptyMutableLiveEvent
import com.healios.dreams.util.Event

class PersonalInformationViewModel(
    private val accountInformationManager: AccountInformationManager,
    private val tokenProvider: TokenProvider,
    private val accountInfoProvider: AccountInfoProvider
) : ViewModel() {

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _isFormValid = MutableLiveData<Boolean>(true)
    val isFormatValid: LiveData<Boolean> = _isFormValid

    private val _agreeTermsAndConditions = MutableLiveData<Boolean>(false)
    val agreeTermsAndConditions: LiveData<Boolean> = _agreeTermsAndConditions

    private val _errorText = MutableLiveData<String>("")
    val errorText: LiveData<String> = _errorText

    private val _acceptedNickname = EmptyMutableLiveEvent()
    val acceptedNickname: EmptyLiveEvent = _acceptedNickname

    private val _changeAvatarEvent = EmptyMutableLiveEvent()
    val changeAvatarEvent : EmptyLiveEvent = _changeAvatarEvent

    val canContinue = MediatorLiveData<Boolean>()
    var selectAvatarButtonText = MutableLiveData<String>()
    var avatarImageResource = MutableLiveData<Int>(R.drawable.ic_default_profile)


    //region: Init viewmodel
    init {

        canContinue.addSource(_isFormValid) {
            canContinue.value = it && _agreeTermsAndConditions.value!! && !_communicationInProgress.value!!
        }

        canContinue.addSource(_agreeTermsAndConditions) {
            canContinue.value = it && _isFormValid.value!! && !_communicationInProgress.value!!
        }

        canContinue.addSource(_communicationInProgress){
            canContinue.value = !it && _isFormValid.value!! && _agreeTermsAndConditions.value!!
        }

        if (accountInfoProvider.isDefaultAvatar()) {
            selectAvatarButtonText.postValue(DreaMSApp.instance.getString(R.string.personalInformation_selectAvatar))
        }else {
            selectAvatarButtonText.postValue(DreaMSApp.instance.getString(R.string.personalInformation_changeAvatar))
        }



    }
    //endregion

    fun onChangeAvatarPressed() {
        _changeAvatarEvent.postValue(Event(Unit))
    }

    fun continueButtonPressed(nickname: String) {
        checkUniqueNickName(nickname)
    }

    fun onTextChanged(currentText: String) {
        checkAlphabeticCharactersOnly(currentText)
    }

    private fun checkAlphabeticCharactersOnly(currentText: String) {
        val alphabeticMask = Regex("[A-Za-z\\s]+")
        val matches = currentText.matches(alphabeticMask)
        if (!matches) {
            _errorText.postValue(DreaMSApp.instance.resources.getString(R.string.personalInformation_nicknameError))
        }
        _isFormValid.postValue(matches)
    }

    private fun checkUniqueNickName(currentText: String) {
        _communicationInProgress.postValue(true)

        accountInformationManager.checkUniqueNickname(currentText, null).process { _, error ->

            _communicationInProgress.postValue(false)

            if (error == null) {
                _acceptedNickname.postValue(Event(Unit))
            } else {
                _errorText.postValue(DreaMSApp.instance.getString(R.string.personalInformation_nicknameTakenError))
            }
        }
    }

    fun onCheckedChangeListener(checked: Boolean) {
        _agreeTermsAndConditions.postValue(checked)
    }

}
