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