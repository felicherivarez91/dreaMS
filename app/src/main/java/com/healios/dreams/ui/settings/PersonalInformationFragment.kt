package com.healios.dreams.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.databinding.FragmentPersonalinformationBinding


class PersonalInformationFragment: Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(PersonalInformationViewModel::class.java)
    }

    private lateinit var binding: FragmentPersonalinformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalinformationBinding.inflate(inflater, container, false)
        //binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
