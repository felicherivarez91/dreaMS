package com.healios.dreams.repository

import com.healios.dreams.di.InjectorUtils
import com.healios.dreams.model.UserCollectionDataResponse
import com.healios.dreams.model.UserData
import com.healios.dreams.repository.datasource.PatientLocalDataSource
import com.healios.dreams.repository.datasource.PatientRemoteDataSource
import com.healios.dreams.repository.datasource.PatientRepositoryLocalDataSource
import com.healios.dreams.repository.datasource.PatientRepositoryRemoteDataSource
import com.healios.dreams.util.DreaMSDateUtils

interface PatientRepository {

    fun getPatientData(responseHandler: (UserData?, Throwable?) -> Unit)
}

class PatientDataRepository : PatientRepository {

    private val patientLocalDataSource: PatientLocalDataSource = PatientRepositoryLocalDataSource()
    private val patientRemoteDataSource: PatientRemoteDataSource = PatientRepositoryRemoteDataSource()
    
    override fun getPatientData(responseHandler: (UserData?, Throwable?) -> Unit) {
        var patientData: UserData

        val localData = patientLocalDataSource.getPatientData()
        if (localData == null){
            askServerForData(responseHandler)
        }else{
            // Data is outdated, proceed to update
            if (dataIsOutDated(localData)) {
                askServerForData(responseHandler)
            } else {
                responseHandler(localData.data, null)
            }
        }
    }

    //region: Utils
    private fun askServerForData(responseHandler: (UserData?, Throwable?) -> Unit) {
        val userId = InjectorUtils.getUserPreferences().userId
        if(userId != null){
            patientRemoteDataSource.askServerForPatientData(userId) { userCollectionData, error ->

                if (error == null) {
                    userCollectionData?.let { userCollectionDataResponse ->
                        patientLocalDataSource.savePatientData(userCollectionDataResponse)
                        responseHandler(userCollectionDataResponse.data, null)

                    }

                    responseHandler(null, Throwable("Retrieved patient data is empty"))

                } else {
                    responseHandler(null, error)
                }
            }
        }else{
            responseHandler(null, Throwable("User Id is null"))
        }
    }

    private fun dataIsOutDated(it: UserCollectionDataResponse): Boolean {
        val weekEndsOn = it.data.patient.attendance.currentAttendance.weekEndsOn
        return DreaMSDateUtils.givenDateIsOutdated(weekEndsOn)
    }
    //endregion


}