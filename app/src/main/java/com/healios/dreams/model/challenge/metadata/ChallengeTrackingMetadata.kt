package com.healios.dreams.model.challenge.metadata
import com.healios.sourcetracker.TrackerService

data class ChallengeTrackingMetadata(
    val sources: List<TrackerService.TrackerSource>,
    val interval: Double ) {

}