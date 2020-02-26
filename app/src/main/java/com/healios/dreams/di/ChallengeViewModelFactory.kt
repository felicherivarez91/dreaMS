package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.model.challenge.ChallengeWorkingData
import com.healios.dreams.ui.challenge.summary.ChallengeSummaryViewModel

@Suppress("UNCHECKED_CAST")
class ChallengeViewModelFactory(val challenge: ChallengeWorkingData) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ChallengeSummaryViewModel::class.java) ->
                    ChallengeSummaryViewModel(challenge, InjectorUtils.getPermissionManager())
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T


}