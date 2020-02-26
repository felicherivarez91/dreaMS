package com.healios.dreams.ui.dashboard

import android.content.Context
import android.text.Spanned
import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R
import com.healios.dreams.data.*
import com.healios.dreams.model.Day
import com.healios.dreams.model.UserCollectionDataResponse
import com.healios.dreams.util.DreaMSDateUtils


class DashboardViewModel constructor(
    private val userManager: UserManager,
    private val tokenProvider: TokenProvider,
    private val userCollectionDataRepository: UserCollectionDataRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val TAG: String = DashboardViewModel::class.java.simpleName


    private var userCollectionData: UserCollectionDataResponse? = null
    private val context:Context = DreaMSApp.instance.applicationContext

    private lateinit var currentDay:Day
    private lateinit var currentDateString:String

    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _isDemoMode = MutableLiveData<Boolean>(true)
    val isDemoMode:LiveData<Boolean> = _isDemoMode

    private val _headerTitleText = MutableLiveData<Spanned>()
    val headerTitleText: LiveData<Spanned> = _headerTitleText

    private val _allCompletedForToday = MutableLiveData<Boolean>(false)
    val allCompletedForToday:LiveData<Boolean> = _allCompletedForToday

    private val _numOfChallengesCompleted = MutableLiveData<String>()
    val numOfChallengesCompleted: LiveData<String> = _numOfChallengesCompleted

    private val _percentOfChallengesCompleted = MutableLiveData<Long>(0)
    val percentOfChallengesCompleted:LiveData<Long> = _percentOfChallengesCompleted

    private val _weekDateText = MutableLiveData<String>()
    val weekDateText: LiveData<String> = _weekDateText

    private val _mondayPercentOfCompletedChallenges = MutableLiveData<Int>(0)
    val mondayPercentOfCompletedChallenges: LiveData<Int> = _mondayPercentOfCompletedChallenges


    //region: Initializer
    init {
        //FIXME: Uncomment
        //askServerForData()

        retrieveUserCollectionData()

        setData()

    }
    //endregion

    private fun setData() {
        if (userCollectionData != null ){

            // Get important information
            getInitialData()

            // Header Info
            setHeaderInfo()

        }
    }

    private fun getInitialData() {
        currentDateString = DreaMSDateUtils.getTodayDateString()
        Log.d(TAG,"[HOY] "+currentDateString)
        val daysOfTheCurrentWeek = userCollectionData!!.data.patient.attendance.currentAttendance.days

        val currentDayList = daysOfTheCurrentWeek.filter { day ->
            day.dateScheduled.equals(currentDateString)
        }
        currentDay = currentDayList.first()



    }

    private fun setHeaderInfo() {

        //Set Demo Mode
        setDemoMode()

        // Set title
        setUserName()

        // Show remaining challenges for the week
        setRemainingChallengesForTheWeek()

        // Set explanation text
        setExplanationText()

        // Set "Your week" Data
        setYourWeekData()
    }

    private fun setYourWeekData() {

        // Set Week Date
        setYourWeekDate()

        // Set Week Days

        val daysOfCurrentAttendance = userCollectionData!!.data.patient.attendance.currentAttendance.days

        daysOfCurrentAttendance.forEach { currentDay ->
            manageDays(currentDay)
        }

    }

    private fun manageDays(currentDay: Day){
        val dayOfTheWeekFromDateString = DreaMSDateUtils.getDayOfTheWeekFromDateString(currentDay.dateScheduled)
        Log.d(TAG,dayOfTheWeekFromDateString)

        when(dayOfTheWeekFromDateString.substring(0,3)){
            "lun" -> configureMonday(currentDay)
        }
    }

    private fun configureMonday(day: Day) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d(TAG, "Configuring Monday day ... ")

        _mondayPercentOfCompletedChallenges.postValue(25/*getPercentOfCompletedChallenges(day)*/)



    }


    private fun setDemoMode() {
        _isDemoMode.postValue(userCollectionData!!.data.studyParticipant.isDemoMode)
    }

    private fun setUserName() {
        val nickname:String = userCollectionData!!.data.patient.nickname

        val userName = nickname.substringBefore(" ")

        val fullNameText:String = context.getString(R.string.fragment_dashboard_header_title, userName)
        val formattedText = HtmlCompat.fromHtml(fullNameText,
            HtmlCompat.FROM_HTML_MODE_COMPACT)

        _headerTitleText.postValue(formattedText)
    }

    private fun setRemainingChallengesForTheWeek() {



        //FIXME: Revisar
        /*
        val numberOfChallenges: Long = userCollectionData!!.data.studyParticipant.numberOfChallenges
        val numberOfChallengesCompleted: Long = userCollectionData!!.data.studyParticipant.numberOfChallengesCompleted

        if (numberOfChallenges != 0.toLong()) {
            _percentOfChallengesCompleted.postValue((numberOfChallengesCompleted / numberOfChallenges) * 100)
        }else {
            _percentOfChallengesCompleted.postValue(0)
        }

        _numOfChallengesCompleted.postValue(String.format("%d / %d",numberOfChallengesCompleted, numberOfChallenges))
         */

    }

    private fun setExplanationText() {
        //TODO("not implemented")
    }


    private fun setYourWeekDate() {

        val weekStartsOn =
            userCollectionData!!.data.patient.attendance.currentAttendance.weekStartsOn
        val weekEndsOn =
            userCollectionData!!.data.patient.attendance.currentAttendance.weekEndsOn

        _weekDateText.postValue(DreaMSDateUtils.getCurrentWeek(weekStartsOn, weekEndsOn))

    }


    //region: API Calls
    private fun askServerForData() {
        _communicationInProgress.postValue(true)

        var userId = userPreferences.userId
        if (userId == null) {
            //TODO: Remove after test API
            userId = "a357fbe8-53d6-11ea-b538-0242ac17000b"
            //return
        }

        userManager.getUserCollectionById(userId, UserType.PATIENT,null).process { userDataModel, error ->

            if (error == null){
                if (userDataModel != null) {
                    //Save data into asset
                    userCollectionDataRepository.saveUserCollectionDataLocally(userDataModel)
                }else{
                    //TODO: Error, data is null
                    Log.e(TAG, "[ERROR] User Collection Data is null!")
                }
            }else{
                //TODO: Error in API response
                Log.e(TAG, error.localizedMessage ?: "[ERROR] in response!")
            }
            _communicationInProgress.postValue(false)
        }

    }
    //endregion

    //region: Local file data
    fun retrieveUserCollectionData() {
        userCollectionData = userCollectionDataRepository.getUserCollectionData()
    }


    //endregion

    //region: Utils
    private fun getPercentOfCompletedChallenges(day:Day): Int {
        val numOfTotalChallenges = day.tests?.size ?: 0
        val completedChallenges = day.tests?.filter { it.completedAt != null }
        val numOfCompletedChallenges = completedChallenges?.size ?: 0

        return (numOfCompletedChallenges / numOfTotalChallenges) * 100

    }

    //endregion
}
