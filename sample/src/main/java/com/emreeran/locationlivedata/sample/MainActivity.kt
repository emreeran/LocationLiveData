package com.emreeran.locationlivedata.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.emreeran.locationlivedata.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Timber.plant(Timber.DebugTree())
    }

    override fun onNavigateUp(): Boolean = findNavController(R.id.main_nav_host).navigateUp()
}
