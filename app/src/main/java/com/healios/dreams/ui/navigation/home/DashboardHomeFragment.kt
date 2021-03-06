package com.healios.dreams.ui.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.healios.dreams.databinding.FragmentDashboardHomeBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory
import com.healios.dreams.ui.navigation.DashboardFragment
import com.healios.dreams.util.EventObserver

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

        // setupWithViewPager() para vincular el TabLayout al ViewPager
        binding.tabLayoutFragmentDashboard.setupWithViewPager(binding.viewPagerFragmentDashboardHome)
        binding.viewPagerFragmentDashboardHome.adapter = DashboardHomeViewPageAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        viewModel.categoryChallengesStartButtonPressedChallengesNavigationArgument.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(DashboardHomeFragmentDirections.actionDashboardHomeFragmentToDashboardCategoryChallengesPathFragment(it.category.categoryId, it.selectedDay))
        })
    }
}
