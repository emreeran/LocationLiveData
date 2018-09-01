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

package com.emreeran.locationlivedata.sample.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by Emre Eran on 31.08.2018.
 */
object Permissions {
    const val REQUEST_CODE_PERMISSIONS = 100

    private val locationPermissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    )

    fun requestLocationPermissionsIfNecessary(fragment: Fragment, callback: HasPermissionsCallback) {
        fragment.context?.let {
            if (!checkIfHasLocationPermissions(it)) {
                fragment.requestPermissions(
                        locationPermissions.toTypedArray(), REQUEST_CODE_PERMISSIONS
                )
            } else {
                callback.hasPermissions()
            }
        }
    }

    fun requestLocationPermissionsIfNecessary(activity: Activity, callback: HasPermissionsCallback) {
        if (!checkIfHasLocationPermissions(activity)) {
            ActivityCompat.requestPermissions(
                    activity, locationPermissions.toTypedArray(), REQUEST_CODE_PERMISSIONS
            )
        } else {
            callback.hasPermissions()
        }
    }

    private fun checkIfHasLocationPermissions(context: Context): Boolean {
        var hasPermissions = true

        for (permission in locationPermissions) {
            hasPermissions = hasPermissions and (ContextCompat.checkSelfPermission(
                    context, permission) == PackageManager.PERMISSION_GRANTED)
        }

        return hasPermissions
    }

    interface HasPermissionsCallback {
        fun hasPermissions()
    }
}