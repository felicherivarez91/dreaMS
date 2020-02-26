package com.healios.dreams.data

import android.util.Log
import com.healios.dreams.model.UserCollectionDataResponse
import com.healios.dreams.util.JSONUtils

interface UserCollectionDataRepository {

    fun getUserCollectionData() : UserCollectionDataResponse?
    fun saveUserCollectionDataLocally(collectionDataObject: Any)

}


class JSONUserCollectionDataRepository : UserCollectionDataRepository{

    companion object {
        const val COLLECTION_DATA_FILENAME = "UserCollectionData.json"
    }

    override fun getUserCollectionData() : UserCollectionDataResponse? {
        return JSONUtils.readDataModelFromJSONFile<UserCollectionDataResponse>(COLLECTION_DATA_FILENAME, UserCollectionDataResponse::class.java)
    }

    override fun saveUserCollectionDataLocally(collectionDataObject: Any) {
        JSONUtils.writeJSONtoFile(COLLECTION_DATA_FILENAME, collectionDataObject)
    }




}