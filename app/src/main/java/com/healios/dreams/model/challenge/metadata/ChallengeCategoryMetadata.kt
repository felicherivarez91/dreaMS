package com.healios.dreams.model.challenge.metadata

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ChallengeCategoryMetadata(@StringRes val description: Int,
                                val estimatedTime: String,
                                val challengesCompleted: Int,
                                val numOfChallenges:Int,
                                @DrawableRes val iconCompleted: Int,
                                @DrawableRes val iconUncompleted: Int ) {
}