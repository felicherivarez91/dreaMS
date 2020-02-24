package com.healios.dreams.ui.quicktour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentQuicktourStepBinding
import com.healios.dreams.di.LoginViewModelFactory
import com.healios.dreams.di.QuickTourViewModelFactory
import com.healios.dreams.ui.login.LoginViewModel

class QuickTourFragment : Fragment() {

    companion object {
        fun newInstance() = QuickTourFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(activity!!, QuickTourViewModelFactory()).get(
            QuickTourViewModel::class.java)
    }
    private lateinit var binding: FragmentQuicktourStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuicktourStepBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()
        return binding.root
    }

    private fun bind() {


        viewModel.quicktourFinished.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_quickTourFragment_to_permissionFragment)
        })


    }


}
