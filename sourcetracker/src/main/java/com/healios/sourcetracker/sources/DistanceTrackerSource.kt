package com.healios.sourcetracker.sources

import android.util.Log
import com.google.android.gms.fitness.SensorsClient
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
import com.healios.sourcetracker.TrackerService
import java.util.concurrent.TimeUnit


class DistanceTrackerSource(private val sensorsClient: SensorsClient, private val interval: Long)
    : TrackerService.TrackerSource {

    private var listener: OnDataPointListener? = null

    private var totalDistance: Float = 0f
    private var distanceDelta: Float = 0f

    companion object {
        const val LOG_TAG = "DistanceTrackerSource"
    }

    init {
        registerListener()
    }

    private fun registerListener() {

        listener = OnDataPointListener {
                dataPoint ->
            dataPoint.dataType.fields.forEach { field ->
                val value = dataPoint.getValue(field)
                Log.i(LOG_TAG, "Detected DataPoint field: " + field.name)
                Log.i(LOG_TAG, "Detected DataPoint value: $value")
                distanceDelta = value.asFloat()
                totalDistance += value.asFloat()
            }
        }

        sensorsClient
            .add(
                SensorRequest.Builder()
                    .setDataType(DataType.TYPE_DISTANCE_DELTA) // Can't be omitted.
                    .setSamplingRate(interval, TimeUnit.MILLISECONDS)
                    .setFastestRate(interval.toInt(),TimeUnit.MILLISECONDS)
                    .build(),
                listener
            )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(LOG_TAG, "Listener registered!")
                } else {
                    Log.e(LOG_TAG, "Listener not registered.", task.exception)
                }
            }
    }

    fun unregisterListener() {

        if(listener == null) {
            return
        }

        sensorsClient
            .remove(listener)
            .addOnCompleteListener {task ->
                if (task.isSuccessful && task.result!!) {
                    Log.i(LOG_TAG, "Listener was removed!")
                } else {
                    Log.i(LOG_TAG, "Listener was not removed.")
                }
            }
    }


    override fun getData(): List<Any?> {
        return listOf(totalDistance)
    }

    override fun getMagnitudNames(): List<String> {
        return listOf("distance")
    }



}