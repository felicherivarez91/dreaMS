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
import com.healios.dreams.data.api.ApiException
import com.healios.dreams.util.EmptyLiveEvent
import com.healios.dreams.util.EmptyMutableLiveEvent
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

    private val _displayError = MutableLiveData<Boolean>(false)
    val displayError: LiveData<Boolean> = _displayError

    private val _errorText = MutableLiveData<String>("")
    val errorText: LiveData<String> = _errorText


    private val _canSaveSchedule = MutableLiveData<Boolean>(false)
    val canSaveSchedule: LiveData<Boolean> = _canSaveSchedule

    private val _selectedDays = MutableLiveData<ArrayList<Int>>(ArrayList(7))
    val selectedDays: LiveData<ArrayList<Int>> = _selectedDays

    val canContinue = MediatorLiveData<Boolean>()

    private val _scheduleSettedUp = EmptyMutableLiveEvent()
    val scheduleSettedUp : EmptyLiveEvent = _scheduleSettedUp


    private lateinit var nickname: String
    private var avatar: Int = 0

    companion object {
        private val startIndex = 0
        private val endIndex = 3

        val monday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_monday)
            .subSequence(startIndex, endIndex)
        val tuesday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_tuesday)
            .subSequence(startIndex, endIndex)
        val wednesday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_wednesday)
            .subSequence(startIndex, endIndex)
        val thursday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_thursday)
            .subSequence(startIndex, endIndex)
        val friday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_friday)
            .subSequence(startIndex, endIndex)
        val saturday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_saturday)
            .subSequence(startIndex, endIndex)
        val sunday = DreaMSApp.instance.applicationContext.getString(R.string.all_day_sunday)
            .subSequence(startIndex, endIndex)
    }

    //region: Init
    init {

        canContinue.addSource(_communicationInProgress) {
            canContinue.value = !it && _canSaveSchedule.value!!
        }

        canContinue.addSource(_canSaveSchedule) {
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

        when (button?.text) {
            monday -> updateSelectedDays(0, if (checked) 1 else 0)
            tuesday -> updateSelectedDays(1, if (checked) 1 else 0)
            wednesday -> updateSelectedDays(2, if (checked) 1 else 0)
            thursday -> updateSelectedDays(3, if (checked) 1 else 0)
            friday -> updateSelectedDays(4, if (checked) 1 else 0)
            saturday -> updateSelectedDays(5, if (checked) 1 else 0)
            sunday -> updateSelectedDays(6, if (checked) 1 else 0)
            else -> Log.d(TAG, "OTRO")
        }

    }

    fun onDoneButtonPressed() {
        _communicationInProgress.postValue(true)

        val schedule = getScheduleInStringFormat()

        accountInformationManager.completeAccountDetails(nickname, avatar, schedule)
            .process { _, error ->
                _communicationInProgress.postValue(false)
                if (error == null) {
                    _scheduleSettedUp.postValue(Event(Unit))
                } else {
                    _displayError.postValue(true)
                    if (error is ApiException) {
                        _errorText.postValue(error.message)
                    }
                }
            }
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

        _canSaveSchedule.postValue(numOfSelectedDays == minNumOfSelectedDays)
    }

    private fun getScheduleInStringFormat(): String {
        var selectedDaysString = ""

        val builder: StringBuilder = StringBuilder("")
        selectedDays.value!!.forEach { isSelected ->
            builder.append(isSelected)
            builder.append(",")
        }
        builder.deleteCharAt(builder.lastIndexOf(","))
        selectedDaysString = builder.toString()


        return selectedDaysString
    }


    fun withArgs(args: ScheduleFragmentArgs) {
        this.avatar = args.avatarResourceId
        this.nickname = args.nickname
    }


}

