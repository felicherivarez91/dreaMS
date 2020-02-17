package com.healios.dreams.ui.login

import androidx.lifecycle.*
import com.healios.dreams.data.LoginManager
import com.healios.dreams.util.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel constructor(private val loginManager: LoginManager ): ViewModel() {

    val phoneNumber = MutableLiveData<String>()
    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _acceptedPhoneEvent = EmptyMutableLiveEvent()
    val acceptedPhoneEvent: EmptyLiveEvent = _acceptedPhoneEvent

    private val _acceptedPhoneErrorEvent = MutableLiveEvent<Throwable>()
    val acceptedPhoneErrorEvent: LiveEvent<Throwable> = _acceptedPhoneErrorEvent

    private val _isFormValid =  MutableLiveData<Boolean>(true)

    val canContinue = MediatorLiveData<Boolean>()

    init {
        canContinue.addSource(communicationInProgress) {
            canContinue.value = !it && _isFormValid.value!!
        }
        canContinue.addSource(_isFormValid) {
            canContinue.value = it && !communicationInProgress.value!!
        }
    }

    fun login() {
        _communicationInProgress.postValue(true)
        loginManager.signin(phoneNumber.value!!.trim()).process {
            _ , error ->

            _communicationInProgress.postValue(false)

            if (error == null) {
                _acceptedPhoneEvent.postValue(Event(Unit))
            }else {
                _acceptedPhoneErrorEvent.postValue(Event(error))
            }
        }
    }


}