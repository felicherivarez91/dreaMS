package com.healios.dreams.model

import com.healios.dreams.DreaMSApp
import com.healios.dreams.util.DreaMSDateUtils
import java.util.*
import kotlin.collections.ArrayList

data class UserCollectionDataResponse(
    val data: UserData
)

data class UserData(
    val studyParticipant: StudyParticipant,
    val patient: Patient,
    val accounts: Accounts,
    val study: Study,
    val uuid: String,
    val meta: Meta,
    val type: String,
    val personal: Personal,
    val notifications: List<Notification>
)

data class Accounts(
    val dreams: Dreams
)

data class Dreams(
    val acceptedTCS: Long,
    val appConfig: AppConfig,
    val acceptedPrivacyPolicy: Long,
    val lastLogin: Long
)

data class AppConfig(
    val attendanceRecordsPeriod: Long,
    val attendanceRecordsHistory: Long
)

data class Meta(
    val dateLastModified: Long,
    val dateCreated: Long
)

data class Notification(
    val readAt: Any? = null,
    val action: String,
    val notificationID: String,
    val sendAt: Long,
    val actionParams: String,
    val nickname: String,
    val message: String,
    val createdAt: Long,
    val icon: String
)

data class Patient(
    val avatar: Long,
    val nickname: String,
    val attendance: Attendance,
    val relapses: List<Relapse>,
    val uuid: String
)

//region: Patient Extensions
fun Patient.activeDays(): List<Int> {
    var startOfWeek =
        DreaMSDateUtils.getDateFromDateString(attendance.currentAttendance.weekStartsOn)
    var activesDaysAux = ArrayList<Int>()

    attendance.currentAttendance.days.forEach { dailyChallenge ->
        val dailyChallengeDate: Date =
            DreaMSDateUtils.getDateFromDateString(dailyChallenge.dateScheduled)
        activesDaysAux.add(
            DreaMSDateUtils.dayDifferenceBetween(
                startOfWeek,
                dailyChallengeDate
            ) + 1
        )
    }

    return activesDaysAux
}

fun Patient.currentSchedulePosition(): List<Int> {
    var schedule = Array(7) { 0 }
    activeDays().forEach { day ->
        schedule[day - 1] = 1
    }
    return schedule.toList()
}

//endregion

data class Attendance(
    val previousAttendance: CurrentAttendanceClass,
    val currentAttendance: CurrentAttendanceClass
)

data class CurrentAttendanceClass(
    val days: List<Day>,
    val weekStartsOn: String,
    val weekEndsOn: String
)

data class Day(
    val tests: List<Test>? = null,
    val dateScheduled: String
)

data class Test(
    val patientTestUUID: String,
    val peakCode: String? = null,
    val intensityID: Long,
    val restartedAt: Any? = null,
    val testId: Long,
    val code: String,
    val startedAt: Long? = null,
    val cantDoItAt: List<Long>? = null,
    val precardUUID: String? = null,
    val durationSEC: Long,
    val completedAt: Long? = null,
    val categoryID: Long
)

data class Relapse(
    val createdAt: Long,
    val id: String,
    val event: String,
    val phaseThreeDate: String? = null,
    val phaseTwoCompletedAt: Long? = null,
    val phaseThreeCompletedAt: Any? = null,
    val phaseOneCompletedAt: Long,
    val phaseOneDate: String,
    val closedAt: Long? = null,
    val phaseTwoDate: String
)

data class Personal(
    val language: String,
    val mobilePhoneNumber: String,
    val firstName: Any? = null,
    val lastName: Any? = null,
    val email: Any? = null,
    val address: Address
)

data class Address(
    val state: Any? = null,
    val zipCode: Any? = null,
    val city: Any? = null,
    val country: Any? = null,
    val address: Any? = null
)

data class Study(
    val id: Long,
    val prefix: String,
    val dateStart: String,
    val dateEnd: String
)

data class StudyParticipant(
    val dateStart: String,
    val subjectType: Long,
    val currentTrackStatus: Long,
    val genderID: Long,
    val health: Health,
    val appCredentials: Long,
    val archivedAt: Any? = null,
    val studyID: Long,
    val currentStatusID: Long,
    val numberOfChallengesCompleted: Long,
    val uuid: String,
    val isHealthyParticipant: Long,
    val hasSignedIcf: Long,
    val onBoardingData: OnBoardingData,
    val dateEnd: String,
    val dateExitInterview: Any? = null,
    val completedAt: Any? = null,
    val subjectID: String,
    val numberOfChallenges: Long,
    val birthdate: String,
    val canSetExitInterview: Long,
    val isDemoMode: Boolean,
    val droppedOutAt: Any? = null,
    val fitbitStatus: String,
    val notes: Any? = null,
    val wizardStep: Long
)

data class Health(
    val height: Long,
    val weight: Long
)

data class OnBoardingData(
    val abilityData: AbilityData,
    val screeningData: ScreeningData
)

data class AbilityData(
    val balanceProblems: Long,
    val challengeIDCantPerform: String,
    val dominantHand: Long,
    val walkingAids: Long
)

data class ScreeningData(
    val mobilePlatform: String,
    val flameDodge: Long,
    val osVersion: Long,
    val phoneModel: Long,
    val glasses: Long,
    val acuity: Long
)

