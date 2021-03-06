/*
 * Copyright (C) 2018 Emre Eran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emreeran.locationlivedata

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*

/**
 * Created by Emre Eran on 31.08.2018.
 */
class LocationLiveData private constructor() : LiveData<Location>() {

    lateinit var context: Context
    lateinit var locationRequest: LocationRequest
    var onErrorCallback: OnErrorCallback? = null
    private lateinit var locationClient: FusedLocationProviderClient

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult ?: return
            for (location in locationResult.locations) {
                postValue(location)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        locationClient = FusedLocationProviderClient(context)

        // Check permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            // Check settings
            val locationSettingsRequest = LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest).build()

            val settingsClient = LocationServices.getSettingsClient(context)
            settingsClient.checkLocationSettings(locationSettingsRequest).addOnCompleteListener {
                try {
                    it.getResult(ApiException::class.java)
                    locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                } catch (e: ApiException) {
                    onErrorCallback?.onLocationSettingsException(e)
                }
            }
        } else {
            onErrorCallback?.onPermissionsMissing()
        }
    }

    override fun onInactive() {
        super.onInactive()
        locationClient.removeLocationUpdates(locationCallback)
    }

    interface OnErrorCallback {
        /**
         * Called when location setting check fails
         * Status codes include:
         * [LocationSettingsStatusCodes.RESOLUTION_REQUIRED]
         * [LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE]
         */
        fun onLocationSettingsException(e: ApiException)

        fun onPermissionsMissing()
    }

    companion object {
        @JvmOverloads
        @JvmStatic
        fun create(
                context: Context,
                interval: Long? = null,
                fastestInterval: Long? = null,
                priority: Int? = null,
                smallestDisplacement: Float? = null,
                expirationTime: Long? = null,
                maxWaitTime: Long? = null,
                numUpdates: Int? = null,
                onErrorCallback: OnErrorCallback? = null
        ): LocationLiveData {
            val liveData = LocationLiveData()
            val locationRequest = LocationRequest.create()

            interval?.let { locationRequest.interval = it }
            fastestInterval?.let { locationRequest.fastestInterval = it }
            priority?.let { locationRequest.priority = it }
            smallestDisplacement?.let { locationRequest.smallestDisplacement = it }
            expirationTime?.let { locationRequest.expirationTime }
            maxWaitTime?.let { locationRequest.maxWaitTime = it }
            numUpdates?.let { locationRequest.numUpdates = it }

            liveData.context = context
            liveData.locationRequest = locationRequest
            liveData.onErrorCallback = onErrorCallback

            return liveData
        }
    }
}
