package com.healios.sourcetracker.sources

import com.healios.sourcetracker.LocationTracker
import com.healios.sourcetracker.TrackerService

class LocationTrackerSource(
    private val locationTracker: LocationTracker,
    private val magnitude: Int,
    private val magnitudeName: String? = null) :
    TrackerService.TrackerSource {

    companion object {
        const val ALTITUDE = 0
        const val LONGITUDE = 1
        const val LATITUDE = 2
        const val SPEED = 3
    }


    override fun getData(): List<Any?> {

        val lastLocation = locationTracker.getLastLocation() ?: return listOf(null)

        return when (magnitude) {
            ALTITUDE -> listOf(lastLocation.altitude)
            LONGITUDE -> listOf(lastLocation.longitude)
            LATITUDE -> listOf(lastLocation.latitude)
            SPEED -> listOf(lastLocation.speed)
            else -> listOf(null)
        }
    }

    override fun getMagnitudNames(): List<String> {

        if(magnitudeName != null) return listOf(magnitudeName)

        return when (magnitude) {
            ALTITUDE -> listOf("elevation")
            LONGITUDE -> listOf("longitude")
            LATITUDE -> listOf("latitude")
            SPEED -> listOf("speed")
            else -> listOf("")
        }
    }


}