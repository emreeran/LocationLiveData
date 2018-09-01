package com.emreeran.locationlivedata.sample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.emreeran.locationlivedata.LocationLiveData
import com.google.android.gms.location.LocationRequest

/**
 * Created by Emre Eran on 31.08.2018.
 */
class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationLiveData = LocationLiveData.create(
            application,
            interval = 100,
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    )

    fun getLocation(): LocationLiveData {
        return locationLiveData
    }
}