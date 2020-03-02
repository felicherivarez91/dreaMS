package com.healios.dreams.model.challenge.runtime

import com.healios.dreams.model.challenge.metadata.ChallengeTrackingMetadata

abstract class ChallengeRuntime(
    val instance: ChallengeInstanceData,
    val trackingData: ChallengeTrackingMetadata
)

class ChallengeBaseRuntime(
    instance: ChallengeInstanceData,
    trackingData: ChallengeTrackingMetadata): ChallengeRuntime(instance,trackingData)

class ChallengeFlowRuntime(
    instance: ChallengeInstanceData,
    trackingData: ChallengeTrackingMetadata,
    val items: List<ChallengeFlowItem>,
    val totalTime: Double
): ChallengeRuntime(instance,trackingData)