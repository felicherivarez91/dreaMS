package com.healios.dreams.data

import com.healios.dreams.data.api.ApiResponse
import com.healios.dreams.data.api.DreaMSService
import com.healios.dreams.model.UserCollectionDataResponse
import javax.inject.Singleton

@Singleton
interface UserManager {
    fun getUserCollectionById(userId: String, userType: UserType?, studyPrefix: String? ) : ApiResponse<UserCollectionDataResponse>

}


class DefaultUserManager constructor(private val userService: DreaMSService) : UserManager {

    override fun getUserCollectionById(userId: String, userType: UserType?, studyPrefix: String?): ApiResponse<UserCollectionDataResponse> {
        val DEFAULT_TYPE_VALUE: String = UserType.PATIENT.type  // patient | expert
        val DEFAULT_STUDY_PREFIX_VALUE: String = "USBFEAS"

        return userService.getUserCollectionById(userId, userType?.type ?: DEFAULT_TYPE_VALUE, studyPrefix ?: DEFAULT_STUDY_PREFIX_VALUE)
    }

}

enum class UserType(val type:String) {
    PATIENT("patient"),
    EXPERT("experty")
}
