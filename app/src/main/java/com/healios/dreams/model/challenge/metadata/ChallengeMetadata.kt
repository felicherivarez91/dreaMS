package com.healios.dreams.model.challenge.metadata

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class ChallengeMetadata(val id: Int,
                             @StringRes val name: Int,
                             @DrawableRes val icon: Int,
                             val category: ChallengeCategory,
                             val intensity: ChallengeIntensity,
                             val time: String,
                             @StringRes val description: Int,
                             val available: Boolean,
                             val peakCode: String? = null,
                             @RawRes val descriptionVideo: Int?,
                             @DrawableRes val descriptionImage: Int?,
                             val codeTest: String) {

    val descriptionResource: Int?
        get(){
        return descriptionVideo ?: descriptionImage
    }




}