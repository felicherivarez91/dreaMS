package com.healios.dreams.ui.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentLoginBinding
import com.healios.dreams.di.LoginViewModelFactory
import com.healios.dreams.util.EventObserver

class LoginFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!, LoginViewModelFactory()).get(
            LoginViewModel::class.java)
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.editTextLoginPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        bind()
        return binding.root
    }

    private fun bind() {
        viewModel.acceptedPhoneEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_loginFragment_to_verifyPhoneFragment)
        })
    }

}