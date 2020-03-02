package com.healios.sourcetracker.sources

import com.healios.sourcetracker.TrackerService

class EyeStateTrackerSource :
    TrackerService.TrackerSource {

    var eyesOpen = false

    override fun getData(): List<Any?> {
        return listOf(eyesOpen)
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("eyes")
    }

}
