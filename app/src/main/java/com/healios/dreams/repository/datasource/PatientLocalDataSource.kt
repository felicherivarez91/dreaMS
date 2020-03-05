package com.healios.dreams.repository.datasource

import com.healios.dreams.model.UserCollectionDataResponse
import com.healios.dreams.util.JSONUtils


interface PatientLocalDataSource {

    fun savePatientData(patientDataObject: UserCollectionDataResponse)
    fun getPatientData(): UserCollectionDataResponse?

}

class PatientRepositoryLocalDataSource : PatientLocalDataSource {

    companion object {
        const val COLLECTION_DATA_FILENAME = "PatientCollectionData.json"
    }

    override fun getPatientData(): UserCollectionDataResponse? {
        return JSONUtils.readDataModelFromJSONFile(
            COLLECTION_DATA_FILENAME,
            UserCollectionDataResponse::class.java
        )
    }

    override fun savePatientData(patientDataObject: UserCollectionDataResponse) {
        JSONUtils.writeJSONtoFile(
            COLLECTION_DATA_FILENAME,
            patientDataObject
        )
    }


}