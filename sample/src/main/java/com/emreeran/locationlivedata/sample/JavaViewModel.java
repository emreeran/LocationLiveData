/*
 * Copyright 2018 Emre Eran
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  The ASF licenses this
 * file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.emreeran.locationlivedata.sample;

import android.app.Application;

import com.emreeran.locationlivedata.LocationLiveData;
import com.google.android.gms.location.LocationRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Created by Emre Eran on 2.09.2018.
 */
@SuppressWarnings("unused") // Java sample
public class JavaViewModel extends AndroidViewModel {

    private LocationLiveData locationLiveData;

    public JavaViewModel(@NonNull Application application) {
        super(application);
        locationLiveData = LocationLiveData.Companion.create(
                application,
                100L,
                null,
                LocationRequest.PRIORITY_HIGH_ACCURACY
        );
    }

    public LocationLiveData getLocationLiveData() {
        return locationLiveData;
    }
}
