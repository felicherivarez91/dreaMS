package com.healios.dreams.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentScheduleBinding
import com.healios.dreams.di.ScheduleViewModelFactory

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val TAG: String? = "ScheduleFragment"
    private lateinit var binding: FragmentScheduleBinding

    private val viewModel by lazy {
        ViewModelProvider(activity!!, ScheduleViewModelFactory()).get(
            ScheduleViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        bind()
        return binding.root
    }

    private fun bind() {

        setupWeekDays()


    }

    private fun setupWeekDays() {

        val weekDays = mapOf(
            ScheduleViewModel.monday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekMonday,
            ScheduleViewModel.tuesday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekTuesday,
            ScheduleViewModel.wednesday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekWednesday,
            ScheduleViewModel.thursday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekThursday,
            ScheduleViewModel.friday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekFriday,
            ScheduleViewModel.saturday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSaturday,
            ScheduleViewModel.sunday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSunday
        )

        for ((k, v) in weekDays){
            v.toggleButtonItemScheduleDay.text = k
            v.toggleButtonItemScheduleDay.textOn = k
            v.toggleButtonItemScheduleDay.textOff = k
            v.toggleButtonItemScheduleDay.setOnCheckedChangeListener { button, isChecked ->
                viewModel.onDaySelectedStatusChanged(button, isChecked)
            }
        }

    }

}
