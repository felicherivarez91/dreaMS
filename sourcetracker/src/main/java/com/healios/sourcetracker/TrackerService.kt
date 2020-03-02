package com.healios.sourcetracker

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.healios.sourcetracker.sources.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class TrackerService(private val activity: Context) {


    private var sources: ArrayList<TrackerSource> = ArrayList()
    private var cancelTracking = false


    private val sensorManager: SensorManager =
        activity.getSystemService(SENSOR_SERVICE) as SensorManager

    private val locationTracker by lazy {
        LocationTracker(activity, interval!!)
    }

    private val fitnessClient by lazy {
        Fitness.getSensorsClient(activity, GoogleSignIn.getLastSignedInAccount(activity)!!)
    }

    private var interval: Long? = null

    var tracks: ArrayList<List<Any?>> = ArrayList()

    interface TrackerSource {
        fun getData() : List<Any?>
        fun getMagnitudNames(): List<String>
    }

    fun stop(){
        cancelTracking = true
        sources.forEach {source ->
            if(source is SensorEventListener){
                sensorManager.unregisterListener(source)
            }
            if (source is DistanceTrackerSource){
                source.unregisterListener()
            }
        }

    }

    fun start(callback: () -> (Unit)) {
        cancelTracking = false

        Timer("RequestSensor", false).schedule(0, interval!!) {
            synchronized(cancelTracking) {
                if(cancelTracking) {
                    cancel()
                    callback()
                }
                val track = ArrayList<Any?>()
                sources.forEach { source ->
                    track.addAll(source.getData())
                }
                tracks.add(track)
            }
        }
    }

    fun setInterval(newInterval: Long) {
        interval = newInterval
    }

    fun addSource(source: TrackerSource){
        sources.add(source)
    }

    fun addAccelerometer() {
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val source =
            AccelerometerTrackerSource()
        sensorManager.registerListener(
            source,
            accelerometer,
            SensorManager.SENSOR_DELAY_FASTEST
        )
        addSource(source)
    }

    fun addGyroscope() {
        val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        val source = GyroscopeTrackerSource()
        sensorManager.registerListener(
            source,
            gyroscope,
            SensorManager.SENSOR_DELAY_FASTEST
        )
        addSource(source)
    }

    fun addStepCounter() {
        val podometer = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        val source = StepSensorTrackerSource()
        sensorManager.registerListener(
            source,
            podometer,
            SensorManager.SENSOR_DELAY_FASTEST
        )
        addSource(source)
    }

    fun addMagnetometer() {
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val source =
            MagnetometerTrackerSource()
        sensorManager.registerListener(
            source,
            magnetometer,
            SensorManager.SENSOR_DELAY_FASTEST
        )
        addSource(source)
    }

    fun addLocationMeasurement(magnitude: Int, custonName: String? = null){
        addSource(
            LocationTrackerSource(
                locationTracker,
                magnitude,
                custonName
            )
        )
    }

    fun addDistanceMeasurement() {
        addSource(
            DistanceTrackerSource(
                fitnessClient,
                interval!!
            )
        )
    }

    fun addOrientation() {
        val source = OrientationTrackerSource()
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            source,
            magnetometer,
            SensorManager.SENSOR_DELAY_FASTEST)

        sensorManager.registerListener(
            source,
            accelerometer,
            SensorManager.SENSOR_DELAY_FASTEST)

        addSource(source)

    }

    fun getSourceMagnitudeNames(): String{
        val magnitudeNamesList = ArrayList<String>()
        sources.forEach { source ->
            magnitudeNamesList.addAll(source.getMagnitudNames())
        }

        return magnitudeNamesList.joinToString()
    }

}

