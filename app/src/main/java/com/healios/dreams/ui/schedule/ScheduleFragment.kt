package com.healios.dreams.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentScheduleBinding
import com.healios.dreams.di.ScheduleViewModelFactory
import com.healios.dreams.ui.account.PersonalInformationFragmentArgs
import com.healios.dreams.util.EventObserver

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

    private val args: ScheduleFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        viewModel.withArgs(args)

        bind()
        return binding.root
    }

    private fun bind() {

        setupWeekDays()

        viewModel.scheduleSettedUp.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_scheduleFragment_to_quickTourFragment)
        })

        viewModel.enableAllButtons.observe(viewLifecycleOwner, Observer {
            enableAllButtons()
        })

        viewModel.disableUnselectedButtons.observe(viewLifecycleOwner, Observer {
            disableUnselectedButtons()
        })
    }

    private fun enableAllButtons(){
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekMonday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekTuesday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekWednesday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekThursday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekFriday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSaturday.toggleButtonItemScheduleDay.isEnabled = true
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSunday.toggleButtonItemScheduleDay.isEnabled = true
    }

    private fun disableUnselectedButtons(){
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekMonday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekMonday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekTuesday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekTuesday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekWednesday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekWednesday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekThursday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekThursday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekFriday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekFriday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSaturday.toggleButtonItemScheduleDay.isEnabled = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSaturday.toggleButtonItemScheduleDay.isChecked
        binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSunday.toggleButtonItemScheduleDay.isEnabled  = binding.itemScheduleWeekFragmentSchedule.itemScheduleDayItemScheduleWeekSunday.toggleButtonItemScheduleDay.isChecked
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
