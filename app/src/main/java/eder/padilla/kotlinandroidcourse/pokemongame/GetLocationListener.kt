package eder.padilla.kotlinandroidcourse.pokemongame

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log

import eder.padilla.kotlinandroidcourse.Util

/**
 * Created by ederpadilla on 12/06/17.
 */

class GetLocationListener(location: Location) : LocationListener {
    var location: Location

    init {
        var location = location
        this.location = location
        location = Location("Location init")
        location.latitude = 0.0
        location.longitude = 0.0
    }

    override fun onLocationChanged(location: Location) {
        this.location = location
        Log.e("::TAG::", "Location :" + location.toString())
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }
}
