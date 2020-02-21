package com.healios.dreams.ui.schedule

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenStarted

import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPersonalinformationBinding
import com.healios.dreams.databinding.FragmentScheduleBinding
import com.healios.dreams.databinding.ItemScheduleDayBinding
import com.healios.dreams.di.PersonalInformationViewModelFactory
import com.healios.dreams.di.ScheduleViewModelFactory
import com.healios.dreams.ui.account.PersonalInformationViewModel

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

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
        val startIndex = 0
        val endIndex = 3

        val monday = getString(R.string.all_day_monday).subSequence(startIndex, endIndex)
        val tuesday = getString(R.string.all_day_tuesday).subSequence(startIndex, endIndex)
        val wednesday = getString(R.string.all_day_wednesday).subSequence(startIndex, endIndex)
        val thursday = getString(R.string.all_day_thursday).subSequence(startIndex, endIndex)
        val friday = getString(R.string.all_day_friday).subSequence(startIndex, endIndex)
        val saturday = getString(R.string.all_day_saturday).subSequence(startIndex, endIndex)
        val sunday = getString(R.string.all_day_sunday).subSequence(startIndex, endIndex)


        val weekDays = mapOf(
            monday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekMonday,
            tuesday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekTuesday,
            wednesday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekWednesday,
            thursday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekThursday,
            friday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekFriday,
            saturday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSaturday,
            sunday to binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSunday
        )

        for ((k, v) in weekDays){
            v.toggleButtonItemScheduleDay.text = k
            v.toggleButtonItemScheduleDay.textOn = k
            v.toggleButtonItemScheduleDay.textOff = k
        }

    }

}
