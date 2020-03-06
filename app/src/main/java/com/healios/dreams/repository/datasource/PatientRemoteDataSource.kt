package com.healios.dreams.repository.datasource

import com.healios.dreams.data.UserType
import com.healios.dreams.di.InjectorUtils.getUserManager
import com.healios.dreams.model.UserCollectionDataResponse

interface PatientRemoteDataSource {

    fun askServerForPatientData(
        userId: String,
        responseHandler: (userCollectionData: UserCollectionDataResponse?, error: Throwable?) -> Unit
    )

}

class PatientRepositoryRemoteDataSource : PatientRemoteDataSource {

    private val TAG: String = "PatientRepositoryRemoteDataSource"

    override fun askServerForPatientData(
        userId: String,
        responseHandler: (userCollectionData: UserCollectionDataResponse?, error: Throwable?) -> Unit
    ) {
        val userManager = getUserManager()
        userManager.getUserCollectionById(userId, UserType.PATIENT, null).process(responseHandler)
    }
}