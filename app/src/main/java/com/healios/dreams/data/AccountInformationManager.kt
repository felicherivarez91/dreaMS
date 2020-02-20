package com.healios.dreams.data

import com.healios.dreams.data.api.ApiResponse
import com.healios.dreams.data.api.DreaMSService
import com.healios.dreams.model.AccounDetailsRequest
import com.healios.dreams.model.UniqueNicknameRequest
import javax.inject.Singleton

@Singleton
interface AccountInformationManager {
    fun checkUniqueNickname(nickname: String, avatar: Int?): ApiResponse<Unit>
    fun completeAccountDetails(nickname: String, avatar: Int?, schedule: String): ApiResponse<Unit>
}

class DefaultAccountInformationManager  constructor(private val accountInformationService: DreaMSService) : AccountInformationManager {

    override fun checkUniqueNickname(nickname: String, avatar: Int?): ApiResponse<Unit> =
        accountInformationService.checkUniqueNickname(UniqueNicknameRequest(nickname, avatar, true))


    override fun completeAccountDetails( nickname: String, avatar: Int?, schedule: String): ApiResponse<Unit> =
        accountInformationService.completeAccountDetails(AccounDetailsRequest(nickname, avatar, schedule))
}