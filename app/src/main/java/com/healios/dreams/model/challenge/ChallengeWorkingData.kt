package com.healios.dreams.model.challenge

data class ChallengeWorkingData(
    val selectedDay: Int,
    val metadata: ChallengeMetadata,
    val patienTestId: String
){

    var startTime: Int? = null
    var endTime: Int? = null
    var testDataBundle: MutableMap<String,Any> = mutableMapOf()

}