package com.healios.dreams.model.challenge.runtime

import com.healios.dreams.model.challenge.metadata.ChallengeMetadata

data class ChallengeInstanceData(
    val selectedDay: Int,
    val metadata: ChallengeMetadata,
    val patientTestId: String
){

    var startTime: Long? = null
    var endTime: Long? = null
    var testDataBundle: MutableMap<String,Any> = mutableMapOf()

}