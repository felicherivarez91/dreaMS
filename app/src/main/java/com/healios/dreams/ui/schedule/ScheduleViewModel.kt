package com.healios.dreams.ui.schedule

import android.util.Log
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R
import com.healios.dreams.data.AccountInfoProvider
import com.healios.dreams.data.AccountInformationManager
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.util.Event

class ScheduleViewModel(
    private val accountInformationManager: AccountInformationManager,
    private val tokenProvider: TokenProvider,
    private val accountInfoProvider: AccountInfoProvider
) : ViewModel() {

    private val TAG: String? = "ScheduleViewModel"

    private val minNumOfSelectedDays = 5

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _canSaveSchedule = MutableLiveData<Boolean>(false)
    val canSaveSchedule: LiveData<Boolean> = _canSaveSchedule

    private val _selectedDays = MutableLiveData<ArrayList<Int>>(ArrayList(7))
    val selectedDays :LiveData<ArrayList<Int>> = _selectedDays

    val canContinue = MediatorLiveData<Boolean>()


    companion object {
        private val startIndex = 0
        private val endIndex = 3

        val monday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_monday).subSequence(startIndex, endIndex)
        val tuesday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_tuesday).subSequence(startIndex, endIndex)
        val wednesday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_wednesday).subSequence(startIndex, endIndex)
        val thursday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_thursday).subSequence(startIndex, endIndex)
        val friday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_friday).subSequence(startIndex, endIndex)
        val saturday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_saturday).subSequence(startIndex, endIndex)
        val sunday =  DreaMSApp.instance.applicationContext.getString(R.string.all_day_sunday).subSequence(startIndex, endIndex)
    }


    //region: Init
    init {

        canContinue.addSource(_communicationInProgress){
            canContinue.value = !it && _canSaveSchedule.value!!
        }

        canContinue.addSource(_canSaveSchedule){
            canContinue.value = it && !_communicationInProgress.value!!
        }

        initializeSelectedDays()


    }
    //endregion

    private fun initializeSelectedDays() {

        val arrayList = _selectedDays.value
        for (x in 1..7)
            arrayList?.add(0)

        _selectedDays.postValue(arrayList)

    }

    fun onDaySelectedStatusChanged(button: CompoundButton?, checked: Boolean) {

        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d(TAG, String.format("Button (%s) state changed to checked = %b", button?.text, checked))

        when(button?.text){
            monday -> updateSelectedDays(0, if(checked) 1 else 0)
            tuesday -> updateSelectedDays(1, if(checked) 1 else 0)
            wednesday-> updateSelectedDays(2, if(checked) 1 else 0)
            thursday -> updateSelectedDays(3, if(checked) 1 else 0)
            friday -> updateSelectedDays(4, if(checked) 1 else 0)
            saturday -> updateSelectedDays(5, if(checked) 1 else 0)
            sunday -> updateSelectedDays(6, if(checked) 1 else 0)
            else -> Log.d(TAG, "OTRO")
        }

    }

    fun onDoneButtonPressed() {
        //TODO: Complete functionality
        _communicationInProgress.postValue(true)

        /*
        accountInformationManager.completeAccountDetails().process { _, error ->

            _communicationInProgress.postValue(false)

            if (error == null) {
            } else {
            }
        }
         */
    }

    private fun updateSelectedDays(position: Int, checked: Int) {
        val list = _selectedDays.value!!
        list[position] = checked

        _selectedDays.postValue(list)

        checkIfCanContinue()
    }

    private fun checkIfCanContinue() {
        val arrayList = _selectedDays.value!!
        val numOfSelectedDays = arrayList.count { it == 1 }

        _canSaveSchedule.postValue(numOfSelectedDays >= minNumOfSelectedDays)

    }


}

