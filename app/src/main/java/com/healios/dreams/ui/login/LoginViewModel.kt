package com.healios.dreams.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.data.CountryRepository
import com.healios.dreams.data.LoginManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.model.CountryModel
import com.healios.dreams.util.*
import com.healios.dreams.util.managers.KeyboardManager

class LoginViewModel constructor(private val loginManager: LoginManager,
                                 private val tokenProvider: TokenProvider): ViewModel() {

    private var selectedCountryPosition: Int = 0

    val selectedCountry = MutableLiveData<CountryModel>()
    val phoneNumber = MutableLiveData<String>()
    val phoneNumberHintText = MutableLiveData<String>()
    var code = MutableLiveData<String>()

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _acceptedPhoneEvent = EmptyMutableLiveEvent()
    val acceptedPhoneEvent: EmptyLiveEvent = _acceptedPhoneEvent

    private val _acceptedPhoneErrorEvent = MutableLiveEvent<Throwable>()
    val acceptedPhoneErrorEvent: LiveEvent<Throwable> = _acceptedPhoneErrorEvent

    private val _shouldShowCountrySelector = MutableLiveData<Boolean>(false)
    val shouldShowCountrySelector: LiveData<Boolean> = _shouldShowCountrySelector

    private val _countriesList = MutableLiveData<List<CountryModel>>()
    val countriesList: LiveData<List<CountryModel>> = _countriesList

    private val _isFormValid =  MutableLiveData<Boolean>(false)
    val isFormValid: LiveData<Boolean> = _isFormValid

    val canContinue = MediatorLiveData<Boolean>()

    init {
        val countries = CountryRepository.getInstance().countries
        _countriesList.postValue(countries)

        val tempSelectedCountry = countries?.get(0)
        tempSelectedCountry?.isSelectedCountry = true
        selectedCountry.value = tempSelectedCountry
        selectedCountry.postValue(tempSelectedCountry)

        setTelephoneHintText(tempSelectedCountry)

        canContinue.addSource(communicationInProgress) {
            canContinue.value = !it && _isFormValid.value!!
        }
        canContinue.addSource(_isFormValid) {
            canContinue.value = it && !communicationInProgress.value!!
        }
    }

    private fun setTelephoneHintText(tempSelectedCountry: CountryModel?) {
        var tempMask = tempSelectedCountry?.telephoneMask
        tempMask = tempMask?.replace("#", "1", true)

        phoneNumberHintText.value = tempMask
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
        _communicationInProgress.postValue(true)
        loginManager.verifyCode(phoneNumber.value!!.trim(), code.value!!.trim()).process {
                response , error ->
            _communicationInProgress.postValue(false)
            if (error == null) {
                _acceptedPhoneEvent.postValue(Event(Unit))
            }else {
                tokenProvider.token = response?.token ?: ""
                _acceptedPhoneErrorEvent.postValue(Event(error))
            }
        }
    }

    //region: Country Selector
    fun onDoneButtonPressed() {
        _shouldShowCountrySelector.postValue(false)
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
    fun setPhoneFormatValid(isValid:Boolean){
        _isFormValid.postValue(isValid)
        _isFormValid.value = isValid
    }

    fun onCountrySelectorPressed() {
        _shouldShowCountrySelector.postValue(true)
    }

    fun hideCountrySelector() {
        _shouldShowCountrySelector.postValue(false)
    }

    fun onCountrySelected(position: Int) {
        selectedCountryPosition = position
        selectCountryForSelectedPosition()
        hideCountrySelector()
    }

    private fun selectCountryForSelectedPosition() {
        selectedCountry.value = countriesList.value?.get(selectedCountryPosition)
        selectedCountry.postValue(countriesList.value?.get(selectedCountryPosition))

        setTelephoneHintText(selectedCountry.value)

        val iterator = countriesList.value?.listIterator()
        val updatedCountries = ArrayList<CountryModel>()
        iterator?.forEach {
            it.isSelectedCountry = (it.equals(selectedCountry.value))
            updatedCountries.add(it)
        }

        _countriesList.postValue(updatedCountries)
    }

    fun onPhoneNumberTextFocusChange(hasFocus: Boolean) {
        if (hasFocus) {
            //Hide Country selector
            _shouldShowCountrySelector.postValue(false)
        }
    }


}