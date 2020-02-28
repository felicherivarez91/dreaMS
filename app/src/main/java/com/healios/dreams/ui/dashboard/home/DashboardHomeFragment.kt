package com.healios.dreams.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentDashboardHomeBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory
import com.healios.dreams.ui.dashboard.DashboardFragment
import com.healios.dreams.ui.dashboard.DashboardHomeViewModel

class DashboardHomeFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(activity!!, DashboardHomeViewModelFactory()).get(
            DashboardHomeViewModel::class.java
        )
    }

    private lateinit var binding: FragmentDashboardHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardHomeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()

        return binding.root
    }

    private fun bind() {

        val tabs = binding.tabLayoutFragmentDashboard
        tabs.addTab(tabs.newTab().setText(getString(R.string.fragment_dashboard_home_tab_categories_text).toUpperCase()))
        tabs.addTab(tabs.newTab().setText(getString(R.string.fragment_dashboard_home_tab_non_completed_text).toUpperCase()))

    }

}
