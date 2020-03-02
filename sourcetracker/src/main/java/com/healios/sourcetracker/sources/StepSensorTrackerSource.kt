package com.healios.sourcetracker.sources

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.healios.sourcetracker.TrackerService

class StepSensorTrackerSource : SensorEventListener,
    TrackerService.TrackerSource {

    var count = 0

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            count++
        }
    }

    override fun getData(): List<Any?> {
        val result = listOf(count)
        count = 0
        return result
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("steps")
    }
}