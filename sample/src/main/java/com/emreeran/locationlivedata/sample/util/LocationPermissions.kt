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