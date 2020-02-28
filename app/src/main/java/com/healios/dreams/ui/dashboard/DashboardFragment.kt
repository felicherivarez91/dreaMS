package com.healios.dreams.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentDashboardBinding
import com.healios.dreams.ui.dashboard.home.DashboardHomeFragment


class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        bind()
        return binding.root
    }

    private fun bind() {


        binding.bottomNavigationViewFragmentDashboard.setOnNavigationItemSelectedListener { item: MenuItem ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> fragment = DashboardHomeFragment.newInstance()
                //R.id.navigation_notifications ->
                //R.id.navigation_relapse ->
                //R.id.navigation_settings ->
                else -> fragment = DashboardHomeFragment.newInstance()
            }
            true
        }
    }






}
