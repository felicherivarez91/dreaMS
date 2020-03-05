package com.healios.dreams.ui.dashboard.home

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
import com.healios.dreams.data.challenge.ChallengeBuilder
import com.healios.dreams.model.*
import com.healios.dreams.model.challenge.metadata.Challenge
import com.healios.dreams.model.challenge.metadata.ChallengeCategory
import com.healios.dreams.model.challenge.metadata.ChallengeCategoryMetadata
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata
import com.healios.dreams.repository.PatientRepository
import com.healios.dreams.util.*

class DashboardHomeViewModel constructor(
    private val userManager: UserManager,
    private val tokenProvider: TokenProvider,
    private val patientRepository: PatientRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val TAG: String = DashboardHomeViewModel::class.java.simpleName


    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress

    private val _isDemoMode = MutableLiveData<Boolean>(true)
    val isDemoMode: LiveData<Boolean> = _isDemoMode

    private val _headerTitleText = MutableLiveData<Spanned>()
    val headerTitleText: LiveData<Spanned> = _headerTitleText

    private val _allCompletedForToday = MutableLiveData<Boolean>(false)
    val allCompletedForToday: LiveData<Boolean> = _allCompletedForToday

    private val _numOfChallengesCompleted = MutableLiveData<String>()
    val numOfChallengesCompleted: LiveData<String> = _numOfChallengesCompleted

    private val _percentOfChallengesCompleted = MutableLiveData<Int>(0)
    val percentOfChallengesCompleted: LiveData<Int> = _percentOfChallengesCompleted

    private val _weekDateText = MutableLiveData<String>()
    val weekDateText: LiveData<String> = _weekDateText

    private val _week = MutableLiveData<List<DayOfTheWeek>>()
    val week: LiveData<List<DayOfTheWeek>> = _week

    //region: Info layout parameters
    private val _infoImageResource = MutableLiveData<Int>()
    val infoImageResource: LiveData<Int> = _infoImageResource

    private val _shouldShowInfoLayout = MutableLiveData<Boolean>(false)
    val shouldShowInfoLayout: LiveData<Boolean> = _shouldShowInfoLayout

    private val _noChallengesScheduled = MutableLiveData<Boolean>(false)
    val noChallengesScheduled: LiveData<Boolean> = _noChallengesScheduled
    //endregion

    private val _dailyNonCompletedChallenges = MutableLiveData<List<ChallengeMetadata>>()
    val dailyNonCompletedChallenges: LiveData<List<ChallengeMetadata>> =
        _dailyNonCompletedChallenges

    private val _challengeCategories = MutableLiveData<List<ChallengeCategoryMetadata>>()
    val challengeCategories: LiveData<List<ChallengeCategoryMetadata>> = _challengeCategories

    private val _categoryChallengesStartButtonPressed =
        MutableLiveEvent<CategoryChallengesNavigationArgument>()
    val categoryChallengesStartButtonPressedChallengesNavigationArgument: LiveEvent<CategoryChallengesNavigationArgument> =
        _categoryChallengesStartButtonPressed

    private val context: Context = DreaMSApp.instance.applicationContext
    private var todayDay: Int = DreaMSDateUtils.getDayOfWeekOfToday()
    private var selectedDay: Int = DreaMSDateUtils.getDayOfWeekOfToday()
    private var userData: UserData? = null
    private var currentDay: Day? = null

    private var patient: Patient?
        get() = userData?.patient
        set(value) {}

    private var dailyChallenges: List<Day>
        get() = patient?.attendance?.currentAttendance?.days ?: ArrayList()
        set(value) {}

    private var todayTests: List<Test>?
        get() {
            val challengePosition = getDailyChallengePosition()
            return if (challengePosition != null) {
                dailyChallenges[challengePosition].tests
            } else {
                null
            }
        }
        set(value) {}

    private var totalChallengesToday: Int
        get() {
            if (dailyChallenges.isEmpty()) {
                return 0
            }
            return todayTests?.size ?: 0
        }
        set(value) {}

    private var challengesCompleted: Int
        get() {
            if (dailyChallenges.isEmpty()) {
                return 0
            }

            return todayTests?.filter { test ->
                test.completedAt != null
            }?.size ?: 0
        }
        set(value) {}

    private var nickname: String
        get() {
            return patient?.nickname ?: ""
        }
        set(value) {}

    private var noChallengesForToday: Boolean
        get() {
            if (dailyChallenges.isEmpty()) {
                return true
            }
            return patient?.currentSchedulePosition()?.get(selectedDay) == 0
        }
        set(value) {}

    private var percentOfCompletedChallenges: Int
        get() {
            if (totalChallengesToday == 0)
                return 0

            return ((challengesCompleted / totalChallengesToday) * 100)
        }
        set(value) {}


    //region: Initializer
    init {
        _communicationInProgress.postValue(true)
        patientRepository.getPatientData { userData, error ->
            _communicationInProgress.postValue(false)
            if (error == null){
                this.userData = userData
                setData()
            }else{
                //TODO: Do something with error!
                println("Error: ${error.localizedMessage}")
            }
        }
    }
    //endregion

    private fun setData() {
        if (userData != null) {

            // Get important information
            getInitialData()

            // Header Info
            setHeaderInfo()
        }
    }

    private fun getInitialData() {
        if (patient!!.activeDays().contains(selectedDay)) {
            val dailyChallengePosition = getDailyChallengePosition()
            currentDay = dailyChallenges[dailyChallengePosition!!]
        }

        showDashboardContentData()
    }

    private fun showDashboardContentData() {
        _shouldShowInfoLayout.postValue((patient!!.currentSchedulePosition()[selectedDay - 1] == 0))
        _noChallengesScheduled.postValue((patient!!.currentSchedulePosition()[selectedDay - 1] == 0))

        if (patient!!.currentSchedulePosition()[selectedDay - 1] != 0) {
            getNonCompletedChallengesForToday()
            setupCategoriesForToday()
        }
    }

    private fun setHeaderInfo() {

        //Set Demo Mode
        setDemoMode()

        // Set title
        setUserName()

        // Show remaining challenges for current day
        setRemainingChallengesForSelectedDay()

        // Set "Your week" Data
        setYourWeekData()
    }

    private fun setYourWeekData() {

        // Set Week Date
        setYourWeekDate()

        // Set Week Days Progress
        setWeekProgress()
    }

    private fun setWeekProgress() {

        val activeDays = patient!!.activeDays()
        val dayNames: ArrayList<String> = ArrayList(7)
        dayNames.add(context.getString(R.string.all_day_monday))
        dayNames.add(context.getString(R.string.all_day_tuesday))
        dayNames.add(context.getString(R.string.all_day_wednesday))
        dayNames.add(context.getString(R.string.all_day_thursday))
        dayNames.add(context.getString(R.string.all_day_friday))
        dayNames.add(context.getString(R.string.all_day_saturday))
        dayNames.add(context.getString(R.string.all_day_sunday))

        val week = ArrayList<DayOfTheWeek>()

        dayNames.forEachIndexed { index, dayName ->
            val dayNumber = index + 1
            val dayDate = DreaMSDateUtils.getDateStringFromDateIncrementedOnDays(
                patient!!.attendance.currentAttendance.weekStartsOn,
                (dayNumber - 1)
            )
            val dailyChallenge: Day? = dailyChallenges.firstOrNull { it.dateScheduled == dayDate }

            val dayOfTheWeek = DayOfTheWeek(
                dayNumber,
                dayName,
                dayDate,
                dailyChallenge,
                DayOfTheWeekStatus.UNAVAILABLE,
                (dayNumber == selectedDay),
                activeDays.indexOf(dayNumber),
                false,
                percentOfCompletedChallenges
            )

            week.add(dayOfTheWeek)
        }
        _week.postValue(week)
    }

    private fun setDemoMode() {
        _isDemoMode.postValue(userData?.studyParticipant?.isDemoMode ?: true)
    }

    private fun setUserName() {

        val userName = nickname.substringBefore(" ")

        val fullNameText: String =
            context.getString(R.string.fragment_dashboard_home_header_title, userName)
        val formattedText = HtmlCompat.fromHtml(
            fullNameText,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )

        _headerTitleText.postValue(formattedText)
    }

    private fun setRemainingChallengesForSelectedDay() {
        val challengesCompletedVsTotal: String =
            String.format("%d/%d", challengesCompleted, totalChallengesToday)
        _numOfChallengesCompleted.postValue(challengesCompletedVsTotal)

        _percentOfChallengesCompleted.postValue(percentOfCompletedChallenges)
    }

    private fun setYourWeekDate() {

        val weekStartsOn =
            patient!!.attendance.currentAttendance.weekStartsOn
        val weekEndsOn =
            patient!!.attendance.currentAttendance.weekEndsOn

        _weekDateText.postValue(DreaMSDateUtils.getCurrentWeek(weekStartsOn, weekEndsOn))

    }

    private fun getNonCompletedChallengesForToday() {
        val schedulePosition = patient!!.currentSchedulePosition()[selectedDay - 1]
        patient!!.attendance.currentAttendance.days[schedulePosition]

        val daysOfTheCurrentWeek =
            patient!!.attendance.currentAttendance.days

        val currentDayList = daysOfTheCurrentWeek.filter { day ->
            day.dateScheduled == DreaMSDateUtils.getTodayDateString()
        }

        val nonCompletedChallenges = currentDayList[0].tests?.filter { test ->
            test.completedAt == null
        }

        val nonCompletedChallengeMetadata: ArrayList<ChallengeMetadata> = ArrayList()
        nonCompletedChallenges?.forEach { currentTest ->

            val challenge = Challenge.values().first {
                it.code == currentTest.testId.toInt()
            }
            val challengeMetadata = ChallengeBuilder.build(challenge)
            nonCompletedChallengeMetadata.add(challengeMetadata)
        }

        _dailyNonCompletedChallenges.postValue(nonCompletedChallengeMetadata)
    }

    //region: Categories grid
    private fun setupCategoriesForToday() {
        val mCategories: ArrayList<ChallengeCategoryMetadata> = ArrayList()

        ChallengeCategory.values().forEach { challengeCategory ->

            val categoryMetadata = ChallengeCategoryMetadata(
                ChallengeCategory.categoryId(challengeCategory).toInt(),
                challengeCategory.description,
                getEstimatedTimeForCategory(challengeCategory),
                getNumOfCompletedChallengesInCategoryForToday(challengeCategory),
                getNumOfChallengesInCategoryForToday(challengeCategory),
                getCompletedIconForCategory(challengeCategory),
                getUncompletedIconForCategory(challengeCategory)
            )

            mCategories.add(categoryMetadata)
        }

        _challengeCategories.postValue(mCategories)
    }

    private fun getEstimatedTimeForCategory(category: ChallengeCategory): String {
        val categoryId = ChallengeCategory.categoryId(category)

        val filteredCategory = todayTests?.filter {
            it.categoryId == categoryId && it.completedAt == null
        }
        val totalSeconds = filteredCategory?.sumBy { it.durationSec.toInt() } ?: 0
        val minutes = totalSeconds / 60
        val rest = totalSeconds % 60

        return if (rest >= 30) {
            String.format("%d-%d min", minutes, minutes + 1)
        } else {
            String.format("%d min", minutes)
        }
    }

    private fun getNumOfCompletedChallengesInCategoryForToday(category: ChallengeCategory): Int {
        val categoryId = ChallengeCategory.categoryId(category)
        val todayTestOfGivenCategory = todayTests?.filter { it.categoryId == categoryId }
        return todayTestOfGivenCategory?.filter { it.completedAt != null }?.size ?: 0
    }

    private fun getNumOfChallengesInCategoryForToday(category: ChallengeCategory): Int {
        val categoryId = ChallengeCategory.categoryId(category)
        return todayTests?.filter { it.categoryId == categoryId }?.size ?: 0
    }

    private fun getCompletedIconForCategory(challengeCategory: ChallengeCategory): Int {
        return when (challengeCategory) {
            ChallengeCategory.COGNITIVE -> R.drawable.ic_challenge_category_cognition_completed
            ChallengeCategory.MOTION -> R.drawable.ic_challenge_category_movement_completed
            ChallengeCategory.FINEMOTORSKILLS -> R.drawable.ic_challenge_category_fine_motor_skills_completed
            ChallengeCategory.VISIONANDSURVEYS -> R.drawable.ic_challenge_category_vision_and_surveys_completed
        }
    }

    private fun getUncompletedIconForCategory(challengeCategory: ChallengeCategory): Int {
        return when (challengeCategory) {
            ChallengeCategory.COGNITIVE -> R.drawable.ic_challenge_category_cognition_uncompleted
            ChallengeCategory.MOTION -> R.drawable.ic_challenge_category_movement_uncompleted
            ChallengeCategory.FINEMOTORSKILLS -> R.drawable.ic_challenge_category_fine_motor_skills_uncompleted
            ChallengeCategory.VISIONANDSURVEYS -> R.drawable.ic_challenge_category_vision_and_surveys_uncompleted
        }
    }
    //endregion

    //region: Public methods
    fun onWeekDayClick(dayClicked: DayOfTheWeek?) {
        if (dayClicked != null) {
            _week.value!!.forEachIndexed { index, day ->
                selectedDay = dayClicked.numOfTheWeek
                day.isSelectedDay = (day == dayClicked)
            }

            _week.postValue(_week.value)

        }
        refreshDashboardData()
    }

    fun onCategoryStartButtonPressed(position: Int, category: ChallengeCategoryMetadata) {
        val todayTestOfGivenCategory =
            todayTests?.filter { it.categoryId == category.categoryId.toLong() }
        todayTestOfGivenCategory?.let {
            val challengesForTodayAndCategory =
                CategoryChallengesNavigationArgument(selectedDay, category)
            _categoryChallengesStartButtonPressed.postValue(Event(challengesForTodayAndCategory))
        }
    }

    fun getTestChallengesOfCategory(categoryId: Int): List<Test>? {
        return todayTests?.filter {
            it.categoryId == categoryId.toLong()
        }
    }
    //endregion

    private fun refreshDashboardData() {
        //Refresh dashboard data
        setRemainingChallengesForSelectedDay()

        showDashboardContentData()
    }

    //region: Utils
    private fun getDailyChallengePosition(): Int? {
        val dcCount: Int? =
            patient?.currentSchedulePosition()?.subList(0, selectedDay - 1)?.filter { it == 1 }
                ?.size
        if (dcCount != null) {
            if (dcCount == 0) {
                return 0
            }
            return dcCount - 1
        } else {
            return 0
        }
    }
    //endregion

}


class DayOfTheWeek(
    val numOfTheWeek: Int,
    val dayName: String,
    val dayDateString: String,
    var dailyChallenge: Day?,
    var status: DayOfTheWeekStatus,
    var isSelectedDay: Boolean,
    val challengePosition: Int,
    var allDailyChallengesCompleted: Boolean,
    var percentOfCompletedChallenges: Int
)

enum class DayOfTheWeekStatus {
    COMPLETED,
    UNCOMPLETED,
    UNAVAILABLE,
    SELECTED
}
