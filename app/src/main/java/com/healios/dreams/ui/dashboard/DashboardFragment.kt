package com.healios.dreams.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.databinding.FragmentDashboardBinding
import com.healios.dreams.di.DashboardViewModelFactory
import com.healios.dreams.di.LoginViewModelFactory
import com.healios.dreams.ui.login.LoginViewModel

class DashboardFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!, DashboardViewModelFactory()).get(
            DashboardViewModel::class.java
        )
    }

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()

        return binding.root
    }

    private fun bind() {

        binding.weekDayFragmentDashboardMonday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Mon"
        binding.weekDayFragmentDashboardTuesday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Tue"
        binding.weekDayFragmentDashboardWednesday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Wed"
        binding.weekDayFragmentDashboardThursday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Thu"
        binding.weekDayFragmentDashboardFriday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Fri"
        binding.weekDayFragmentDashboardSaturday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Sat"
        binding.weekDayFragmentDashboardSunday.textViewFragmentDashboardWeekDayItemDayTitle.text = "Sun"

    }

}
