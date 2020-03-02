package com.healios.dreams.data

import com.healios.dreams.model.challenge.runtime.ChallengeInstanceData

interface TestRepository {

    fun saveTest(challengeInstance: ChallengeInstanceData)
    fun cantDoTest(challengeInstance: ChallengeInstanceData)
    fun restartTest(challengeInstance: ChallengeInstanceData)
    fun saveTest(patientTestId: String)
}