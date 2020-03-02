package com.healios.dreams.model.challenge.runtime

import androidx.annotation.IdRes
import com.healios.sourcetracker.TrackerService

data class ChallengeFlowItem(
    val initialTime: Int,
    @IdRes val finalTime: Int?,
    @IdRes val startText: Int? = null,
    val endText: Int? = null,
    val playBeepAtStart: Boolean = false,
    val playBeepAtEnd: Boolean = false,
    val sendToTrackerService: ((service: TrackerService) -> Unit)?)