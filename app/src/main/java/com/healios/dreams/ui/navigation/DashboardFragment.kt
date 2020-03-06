package com.healios.dreams.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentDashboardBinding


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
        val bottomNavigationView = binding.bottomNavigationViewFragmentDashboard
        val navController =
            childFragmentManager.findFragmentById(R.id.fragment_dashboard_nav_host_fragment)!!.findNavController()
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }







}
