package com.healios.sourcetracker.sources

import com.healios.sourcetracker.TrackerService
import java.util.*

class TimeStampTrackerSource :
    TrackerService.TrackerSource {

    override fun getData(): List<Any?> {
        return listOf(Date().time)
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("timestamp")
    }


}