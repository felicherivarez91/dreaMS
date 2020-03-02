package com.healios.sourcetracker.sources

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.healios.sourcetracker.TrackerService

/* Returns the heading (measured in degrees) relative to true north.*/


class OrientationTrackerSource : SensorEventListener,
    TrackerService.TrackerSource {

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var lastOrientation: Double? = null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
        }
    }

    override fun getData(): List<Any?> {
        updateOrientationAngles()
        return listOf(lastOrientation)
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("compass")
    }


    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )

        val orientation = SensorManager.getOrientation(rotationMatrix, orientationAngles)

        lastOrientation = Math.toDegrees(orientation[0].toDouble())

    }
}