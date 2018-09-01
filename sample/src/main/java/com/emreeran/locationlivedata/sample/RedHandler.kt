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

import android.view.View
import androidx.navigation.findNavController

/**
 * Created by Emre Eran on 31.08.2018.
 */
class RedHandler {
    fun onRightButtonTap(view: View) {
        view.findNavController().navigate(RedFragmentDirections.showBlue())
    }
}