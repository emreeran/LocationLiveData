# LocationLiveData

A simple ```LiveData``` implementation of Android Location API.

Uses ```FusedLocationProviderClient``` as location client.

For more information on ```LiveData``` refer to this [link][livedata].
### Setup
Add ```lifecycle-runtime``` and ```locationlivedata``` to your dependencies:

```
dependencies {

    implementation "androidx.lifecycle:lifecycle-runtime:2.0.0-rc01"
    implementation "com.emreeran.locationlivedata:locationlivedata:1.0.1"

    ...
}
```

Add location permissions to your manifest:

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.emreeran.locationlivedata">

    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    ...

</manifest>
```

### Usage

Create LocationLiveData instance and start observing.
All parameter except context are optional.
Uses defaults of ```LocationRequest``` if not specified.

In Kotlin:

```
val locationLiveData = LocationLiveData.create(
        context,
        interval = 500,
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY,
        expirationTime = 10000,
        fastestInterval = 100,
        maxWaitTime = 1000,
        numUpdates = 10,
        smallestDisplacement = 10f,
        onErrorCallback = object : LocationLiveData.OnErrorCallback {
            override fun onLocationSettingsException(e: ApiException) {
                if (e is ResolvableApiException){
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        e.startResolutionForResult(this@MainActivity, REQUEST_CHECK_SETTINGS)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }
                }
            }

            override fun onPermissionsMissing() {
                // Show message
            }
        }
)

locationLiveData.observe(this, Observer {   // Where this is a lifecycle owner
    // Do something with location update
    currentLocation = it
})
```

In Java:

```
LocationLiveData locationLiveData = LocationLiveData.Companion.create(
        application,
        500L,                                       // Interval
        100L,                                       // Fastest interval
        LocationRequest.PRIORITY_HIGH_ACCURACY,     // Priority
        10F,                                        // Smallest displacement
        10000L,                                     // Expiration time
        10000L,                                     // Max wait time
        10,                                         // Number of updates
        new LocationLiveData.OnErrorCallback() {    // Error callbacks
            @Override
            public void onLocationSettingsException(@NotNull ApiException e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MainActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }

            @Override
            public void onPermissionsMissing() {
                // Show message
            }
        }
);

locationLiveData.observe(this, (Observer<Location>) location -> {
        // Do something with location update
        currentLocation = location
});
```

[livedata]: https://developer.android.com/topic/libraries/architecture/livedata

### License

```
Copyright 2018 Emre Eran

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
```
