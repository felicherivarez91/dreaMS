package com.healios.dreams.ui.quicktour

import android.content.Context
import android.service.dreams.DreamService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R
import com.healios.dreams.data.AccountInformationManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.util.EmptyLiveEvent
import com.healios.dreams.util.EmptyMutableLiveEvent
import com.healios.dreams.util.Event

class QuickTourViewModel(
    accountInformationManager: AccountInformationManager,
    tokenProvider: TokenProvider
) : ViewModel() {


    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _displayError = MutableLiveData<Boolean>(false)
    val displayError: LiveData<Boolean> = _displayError

    private val _errorText = MutableLiveData<String>("")
    val errorText: LiveData<String> = _errorText


    private val _quicktourStepImageResource = MutableLiveData<Int>()
    val quicktourStepImageResource : LiveData<Int> = _quicktourStepImageResource

    private val _quicktourStepTitleText = MutableLiveData<String>("")
    val quicktourStepTitleText: LiveData<String> = _quicktourStepTitleText

    private val _quicktourStepExplanationText = MutableLiveData<String>("")
    val quicktourStepExplanationText: LiveData<String> = _quicktourStepExplanationText

    private val _quicktourStepIndicationImageResource = MutableLiveData<Int>()
    val quicktourStepIndicationImageResource: LiveData<Int> = _quicktourStepIndicationImageResource

    private val _isBackButtonVisible = MutableLiveData<Boolean>(false)
    val isBackButtonVisible: LiveData<Boolean> = _isBackButtonVisible

    private val _quicktourFinished = EmptyMutableLiveEvent()
    val quicktourFinished : EmptyLiveEvent = _quicktourFinished


    private var currentStep = 1
    private var context: Context = DreaMSApp.instance.applicationContext

    //region: Initializer
    init {

        setContentOfStep()

    }
    //endregion


    private fun setContentOfStep() {

        when(currentStep) {
            1 -> setFirstStepContent()
            2 -> setSecondStepContent()
            3 -> setThirdStepContent()
            4 -> exit()
        }
    }

    private fun setFirstStepContent() {
        _quicktourStepIndicationImageResource.postValue(R.drawable.ic_slider_indicators_1_of_3)
        _quicktourStepTitleText.postValue(context.getString(R.string.fragment_quicktour_step_daily_challenges_title_text))
        _quicktourStepExplanationText.postValue(context.getString(R.string.fragment_quicktour_step_daily_challenges_explanation_text))

        _isBackButtonVisible.postValue(false)
    }

    private fun setSecondStepContent() {
        _quicktourStepIndicationImageResource.postValue(R.drawable.ic_slider_indicators_2_of_3)
        _quicktourStepTitleText.postValue(context.getString(R.string.fragment_quicktour_step_record_a_relapse_title_text))
        _quicktourStepExplanationText.postValue(context.getString(R.string.fragment_quicktour_step_record_a_relapse_explanation_text))

        _isBackButtonVisible.postValue(true)
    }

    private fun setThirdStepContent() {
        _quicktourStepIndicationImageResource.postValue(R.drawable.ic_slider_indicators_3_of_3)
        _quicktourStepTitleText.postValue(context.getString(R.string.fragment_quicktour_step_connect_dreams_title_text))
        _quicktourStepExplanationText.postValue(context.getString(R.string.fragment_quicktour_step_connect_dreams_explanation_text))

        _isBackButtonVisible.postValue(true)
    }

    private fun exit() {
        currentStep = 1
        _quicktourFinished.postValue(Event(Unit))
    }


    fun onNextButtonPressed() {
        currentStep++
        setContentOfStep()
    }

    fun onBackButtonPressed() {
        currentStep--
        setContentOfStep()
    }

    fun onSkipButtonPressed() {
        exit()
    }

}
