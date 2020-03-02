package com.healios.sourcetracker.sources

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.healios.sourcetracker.TrackerService

abstract class ThreeDimensionalTrackerSource(private val tag: String? = null) : SensorEventListener,
    TrackerService.TrackerSource {

    var x: Float? = null
    var y: Float? = null
    var z: Float? = null

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {

        if (event != null) {
            x = event.values[0]
            y = event.values[1]
            z = event.values[2]
        }
    }

    override fun getData(): List<Any?> {
        return listOf(x,y,z)
    }
}

class AccelerometerTrackerSource : ThreeDimensionalTrackerSource() {

    override fun getData(): List<Any?> {

        return listOf(normalize(x),normalize(y),normalize(z))
    }

    private fun normalize(value: Float?) : Any?{
        return if (value == null) value else value /8.91
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("accelerometer_x", "accelerometer_y", "accelerometer_z")
    }
}

class GyroscopeTrackerSource : ThreeDimensionalTrackerSource() {


    override fun getMagnitudNames(): List<String> {
        return listOf("gyroscope_x", "gyroscope_y", "gyroscope_z")
    }
}

class MagnetometerTrackerSource : ThreeDimensionalTrackerSource() {


    override fun getMagnitudNames(): List<String> {
        return listOf("magnetometer_x", "magnetometer_y", "magnetometer_z")
    }
}