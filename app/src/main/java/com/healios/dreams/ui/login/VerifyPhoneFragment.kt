package com.healios.dreams.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.healios.dreams.databinding.FragmentVerifyPhoneBinding
import com.healios.dreams.di.LoginViewModelFactory


class VerifyPhoneFragment : Fragment() {


    private lateinit var binding: FragmentVerifyPhoneBinding
    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(
            LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVerifyPhoneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        bind()
        return binding.root
    }

    private fun bind() {

        viewModel.code.observe(this) {
            Log.d("TEST", it)
        }


    }



}