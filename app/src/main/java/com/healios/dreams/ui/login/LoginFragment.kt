package com.healios.dreams.ui.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.databinding.FragmentLoginBinding
import com.healios.dreams.di.LoginViewModelFactory

class LoginFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory()).get(
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
        return binding.root
    }





}