package com.healios.dreams.ui.login

import androidx.lifecycle.*
import com.healios.dreams.DreaMSApp
import com.healios.dreams.data.CountryRepository
import com.healios.dreams.data.LoginManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.data.api.ApiException
import com.healios.dreams.model.CountryModel
import com.healios.dreams.util.*
import com.healios.dreams.R
import com.healios.dreams.data.UserPreferences


class LoginViewModel constructor(private val loginManager: LoginManager,
                                 private val tokenProvider: TokenProvider,
                                 private val countryRepository: CountryRepository,
                                 private val userPreferences: UserPreferences): ViewModel() {

    private var selectedCountryPosition: Int = 0
    val selectedCountry = MutableLiveData<CountryModel>()
    val phoneNumber = MutableLiveData<String>("")
    val code1 = MutableLiveData<String>("")
    val code2 = MutableLiveData<String>("")
    val code3 = MutableLiveData<String>("")
    val code4 = MutableLiveData<String>("")
    val phoneNumberHintText = MutableLiveData<String>()
    var code = MediatorLiveData<String>()

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _acceptedPhoneEvent = EmptyMutableLiveEvent()
    val acceptedPhoneEvent: EmptyLiveEvent = _acceptedPhoneEvent

    private val _verifiedCodeEvent = EmptyMutableLiveEvent()
    val verifiedCodeEvent: EmptyLiveEvent = _verifiedCodeEvent

    private val _startVerificationEvent = EmptyMutableLiveEvent()
    val startVerificationEvent: EmptyLiveEvent = _startVerificationEvent

    private val _showCountrySelector = MutableLiveData<Boolean>(false)
    val showCountrySelector: LiveData<Boolean> = _showCountrySelector

    private val _countriesList = MutableLiveData<List<CountryModel>>()
    val countriesList: LiveData<List<CountryModel>> = _countriesList

    private val _validationError = MutableLiveData<Boolean>(false)
    val validationError: LiveData<Boolean> = _validationError

    private val _verificationError = MutableLiveData<Boolean>(false)
    val verificationError: LiveData<Boolean> = _verificationError

    private val _errorText = MutableLiveData<String>("")
    val errorText: LiveData<String> = _errorText

     val fullPhonenumber = Transformations.map(phoneNumber) {
        selectedCountry.value!!.telephoneCountryCode + " " + phoneNumber.value
    }

    private val _isFormValid =  Transformations.map(fullPhonenumber) {
        val regex =  selectedCountry.value?.validationRegex?.toRegex()
        if(regex == null)
            false
        else {
            it.matches(regex)
        }
    }

    val isFormValid: LiveData<Boolean> = _isFormValid
    val canContinue = MediatorLiveData<Boolean>()

    init {

        setupSelectedCountry()
        setupContinueStatus()
        _errorText.value = DreaMSApp.instance.resources.getString(R.string.login_telephoneErrorMessagePlaceholder)

        addCodeSources()
    }

    private fun setTelephoneHintText(tempSelectedCountry: CountryModel?) {
        var tempMask = tempSelectedCountry?.telephoneMask
        tempMask = tempMask?.replace("#", "1", true)
        phoneNumberHintText.value = tempMask
    }

    fun login() {
        _communicationInProgress.postValue(true)
        userPreferences.defaultCountry = selectedCountry.value!!.countryCode
        loginManager.signin(fullPhonenumber.value!!.trim()).process {
            _ , error ->

            _communicationInProgress.postValue(false)

            if (error == null) {
                _acceptedPhoneEvent.postValue(Event(Unit))
            }else {
                _validationError.postValue(true)
                if (error is ApiException) {
                    _errorText.postValue(error.message)
                }
            }
        }
    }

    fun resendCode() {
        _communicationInProgress.postValue(true)
        loginManager.resendCode(fullPhonenumber.value!!).process {
            _, error ->
            _communicationInProgress.postValue(false)
        }
    }

    private fun verifyCode() {
        _startVerificationEvent.postValue(Event(Unit))
        _communicationInProgress.value = true
        _verificationError.value = false
        loginManager.verifyCode(fullPhonenumber.value!!, code.value!!.trim()).process {
                response , error ->
            if (error == null) {
                tokenProvider.token = response?.token ?: ""
                userPreferences.userId = response?.userId ?: ""
                _verifiedCodeEvent.postValue(Event(Unit))
            }else {
                _verificationError.postValue(true)
                if (error is ApiException) {
                    _errorText.postValue(error.message)
                }
            }
            code4.postValue("")
            code2.postValue("")
            code3.postValue("")
            code1.postValue("")
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

    //region: Country Selector
    fun onDoneButtonPressed() {
        _showCountrySelector.postValue(false)
    }

    fun onPreviousButtonPressed() {
        if (selectedCountryPosition - 1 >= 0){
            selectedCountryPosition -= 1
        }
        selectCountryForSelectedPosition()
    }

    fun onNextButtonPressed() {
        val countriesSize = countriesList.value!!.size
        if (selectedCountryPosition + 1 < countriesSize){
            selectedCountryPosition += 1
        }
        selectCountryForSelectedPosition()
    }
    //endregion

    //region: EditText Format


    fun onCountrySelectorPressed() {
        _showCountrySelector.postValue(true)
    }

    fun hideCountrySelector() {
        _showCountrySelector.postValue(false)
    }

    fun onCountrySelected(position: Int) {
        selectedCountryPosition = position
        selectCountryForSelectedPosition()
        hideCountrySelector()
    }

    private fun setupSelectedCountry() {
        val countries = countryRepository.getCountries()
        _countriesList.postValue(countries)
        val defaultCountry = userPreferences.defaultCountry ?: "CH"
        val tempSelectedCountry = countries.find { country -> country.countryCode == defaultCountry } ?: countries[0]
        tempSelectedCountry.isSelectedCountry = true
        selectedCountry.value = tempSelectedCountry
        selectedCountry.postValue(tempSelectedCountry)
        setTelephoneHintText(tempSelectedCountry)
    }

    private fun setupContinueStatus() {
        canContinue.addSource(communicationInProgress) {
            canContinue.value = !it && _isFormValid.value!!
        }
        canContinue.addSource(_isFormValid) {
            canContinue.value = it && !communicationInProgress.value!!
        }
    }

    private fun selectCountryForSelectedPosition() {
        selectedCountry.value = countriesList.value?.get(selectedCountryPosition)
        selectedCountry.postValue(countriesList.value?.get(selectedCountryPosition))

        setTelephoneHintText(selectedCountry.value)

        val iterator = countriesList.value?.listIterator()
        val updatedCountries = ArrayList<CountryModel>()
        iterator?.forEach {
            it.isSelectedCountry = it == selectedCountry.value
            updatedCountries.add(it)
        }

        _countriesList.postValue(updatedCountries)
    }

    fun setupVerifyCode() {
        code1.postValue("")
        code2.postValue("")
        code3.postValue("")
        code4.postValue("")
        _verificationError.postValue(false)
    }

    fun onPhoneNumberTextFocusChange(hasFocus: Boolean) {
        if (hasFocus) {
            //Hide Country selector
            _showCountrySelector.postValue(false)
            _validationError.value = false
            _errorText.value = DreaMSApp.instance.resources.getString(R.string.login_telephoneErrorMessagePlaceholder)
        }
    }


}