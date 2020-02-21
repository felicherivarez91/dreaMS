package com.healios.dreams.ui.schedule

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider

import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPersonalinformationBinding
import com.healios.dreams.databinding.FragmentScheduleBinding
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


    }


}
