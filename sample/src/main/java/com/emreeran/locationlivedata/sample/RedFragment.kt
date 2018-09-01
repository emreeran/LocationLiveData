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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emreeran.locationlivedata.R
import com.emreeran.locationlivedata.databinding.RedFragmentBinding
import com.emreeran.locationlivedata.sample.util.Permissions
import com.emreeran.locationlivedata.sample.util.autoCleared

/**
 * Created by Emre Eran on 31.08.2018.
 */
class RedFragment : Fragment() {

    var binding by autoCleared<RedFragmentBinding>()

    lateinit var locationViewModel: LocationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.red_fragment, container, false)
        binding.handler = RedHandler()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

        Permissions.requestLocationPermissionsIfNecessary(this, object : Permissions.HasPermissionsCallback {
            override fun hasPermissions() {
                observeLocation()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Permissions.REQUEST_CODE_PERMISSIONS) {
            observeLocation()
        }
    }

    private fun observeLocation() {
        locationViewModel.getLocation().observe(this, Observer {
            binding.location = it
        })
    }
}