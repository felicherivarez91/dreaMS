package com.healios.sourcetracker

import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class LocationTracker(context: Context, interval: Long) {

    private var fusedLocationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private var lastLocation: Location? = null

    private val updateLocation: (Location) -> (Unit) = { location: Location ->
        lastLocation = location
    }

    init {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = interval
        fusedLocationProvider.requestLocationUpdates(
            locationRequest,
            MyLocationCallback(updateLocation),
            null
        )
    }

    class MyLocationCallback(private val locationUpdate: (Location) -> (Unit)) :
        LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            if (locationResult != null) {
                locationUpdate(locationResult.lastLocation)
            }
        }
    }

    fun getLastLocation(): Location?{
        return lastLocation
    }



}
