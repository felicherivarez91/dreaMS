package com.healios.dreams.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healios.dreams.data.*

class DashboardViewModel constructor(
    private val userManager: UserManager,
    private val tokenProvider: TokenProvider,
    private val userCollectionDataRepository: UserCollectionDataRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {


    private val TAG: String = DashboardViewModel::class.java.simpleName
    private val _communicationInProgress = MutableLiveData<Boolean>(false)
    val communicationInProgress: LiveData<Boolean> = _communicationInProgress


    //region: Initializer
    init {

        askServerForData()

    }
    //endregion


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
                    //TODO: Save data into asset
                    userCollectionDataRepository.saveUserCollectionDataLocally(userDataModel)


                    userCollectionDataRepository.getUserCollectionData()



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
}
