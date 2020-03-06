package com.healios.dreams.util

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.healios.dreams.R

fun Fragment.hideBottomNavigationViewIfProceeds() {
    val bottomNavigationView by lazy {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView_fragment_dashboard)
    }

    bottomNavigationView?.let {
        it.visibility = View.GONE
    }
}

fun Fragment.showBottomNavigationViewIfProceeds() {
    val bottomNavigationView by lazy {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView_fragment_dashboard)
    }

    bottomNavigationView?.let {
        it.visibility = View.VISIBLE
    }
}