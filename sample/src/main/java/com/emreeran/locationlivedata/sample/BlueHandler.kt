package com.emreeran.locationlivedata.sample

import android.view.View
import androidx.navigation.findNavController

/**
 * Created by Emre Eran on 31.08.2018.
 */
class BlueHandler {

    fun onRightButtonTap(view: View) {
        view.findNavController().navigate(BlueFragmentDirections.showRed())
    }
}