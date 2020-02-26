package com.healios.dreams.ui.challenge

import androidx.lifecycle.ViewModel
import com.healios.dreams.model.challenge.ChallengeMetadata
import com.healios.dreams.model.challenge.ChallengeWorkingData

abstract class ChallengeViewModel(var workingData: ChallengeWorkingData): ViewModel() {

    val challengeMetadata: ChallengeMetadata
    get() {
        return workingData.metadata
    }

}