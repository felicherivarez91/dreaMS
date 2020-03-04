package com.healios.dreams.util

import com.healios.dreams.data.challenge.ChallengeBuilder
import com.healios.dreams.model.Test
import com.healios.dreams.model.challenge.metadata.Challenge
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata

class ChallengeUtils {
    companion object {

        fun getChallengeMetadataFromTest(test: Test): ChallengeMetadata {

            val challenge = Challenge.values().first {
                it.code == test.testId.toInt()
            }
            return ChallengeBuilder.build(challenge)
        }
    }
}