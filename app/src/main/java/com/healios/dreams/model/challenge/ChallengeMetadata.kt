package com.healios.dreams.model.challenge

data class ChallengeMetadata(val id: Int,
                             val name: String,
                             val icon: String,
                             val category: ChallengeCategory,
                             val intensity: ChallengeIntensity,
                             val time: String,
                             val description: String,
                             val available: Boolean,
                             val peakCode: String? = null,
                             val codeTest: String) {







}