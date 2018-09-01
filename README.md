# LocationLiveData

A simple ```LiveData``` implementation of Android Location API.

Uses ```FusedLocationProviderClient``` as location client.

### Setup

```
dependencies {

    implementation "androidx.lifecycle:lifecycle-runtime:2.0.0-rc01"

    ...
}
```

### Usage

```
val locationLiveData = LocationLiveData.create(
        context,
        interval = 500,
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY,
        expirationTime = 10000,
        fastestInterval = 100,
        maxWaitTime = 1000,
        numUpdates = 10,
        smallestDisplacement = 10f
)

locationLiveData.observe(this, Observer {
    // Do something with location update
    currentLocation = it
})
```

