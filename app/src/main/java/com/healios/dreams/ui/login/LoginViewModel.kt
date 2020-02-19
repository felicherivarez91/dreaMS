package com.healios.dreams.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.data.LoginManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.data.api.ApiException
import com.healios.dreams.util.*

class LoginViewModel constructor(private val loginManager: LoginManager,
                                 private val tokenProvider: TokenProvider): ViewModel() {

    val phoneNumber = MutableLiveData<String>()
    val code1 = MutableLiveData<String>("")
    val code2 = MutableLiveData<String>("")
    val code3 = MutableLiveData<String>("")
    val code4 = MutableLiveData<String>("")

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _acceptedPhoneEvent = EmptyMutableLiveEvent()
    val acceptedPhoneEvent: EmptyLiveEvent = _acceptedPhoneEvent

    private val _verifiedCodeEvent = EmptyMutableLiveEvent()
    val verifiedCodeEvent: EmptyLiveEvent = _verifiedCodeEvent

    private val _acceptedPhoneErrorEvent = MutableLiveEvent<Throwable>()
    val acceptedPhoneErrorEvent: LiveEvent<Throwable> = _acceptedPhoneErrorEvent

    private val _displayError = MutableLiveData<Boolean>(false)
    val displayError: LiveData<Boolean> = _displayError

    private val _errorText = MutableLiveData<String>("")
    val errotText: LiveData<String> = _errorText

    private val _isFormValid =  MutableLiveData<Boolean>(true)

    val canContinue = MediatorLiveData<Boolean>()
    val code = MediatorLiveData<String>()


    init {

        canContinue.addSource(communicationInProgress) {
            canContinue.value = !it && _isFormValid.value!!
        }
        canContinue.addSource(_isFormValid) {
            canContinue.value = it && !communicationInProgress.value!!
        }

        addCodeSources()

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

    fun verifyCode() {
        _communicationInProgress.value = true
        _displayError.value = false
        loginManager.verifyCode(phoneNumber.value!!.trim(), code.value!!.trim()).process {
                response , error ->
            if (error == null) {
                tokenProvider.token = response?.token ?: ""
                _verifiedCodeEvent.postValue(Event(Unit))
            }else {
                _displayError.postValue(true)
                if (error is ApiException) {
                    _errorText.postValue(error.message)
                }
                code1.postValue("")
                code2.postValue("")
                code3.postValue("")
                code4.postValue("")
            }
            _communicationInProgress.postValue(false)

        }
    }

    private fun addCodeSources() {
        code.addSource(code1) {
            code.value = it + code2.value + code3.value + code4.value
            checkCode()
        }

        code.addSource(code2) {
            code.value = code1.value + it + code3.value + code4.value
            checkCode()
        }

        code.addSource(code3) {
            code.value = code1.value + code2.value + it + code4.value
            checkCode()
        }

        code.addSource(code4) {
            code.value = code1.value + code2.value + code3.value + it
            checkCode()
        }
    }


    private fun checkCode() {
        if (code.value!!.length == 4 && !communicationInProgress.value!!) {
            verifyCode()
        }
    }


}