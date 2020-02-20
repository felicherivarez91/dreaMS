package com.healios.dreams.ui.login

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentVerifyPhoneBinding
import com.healios.dreams.di.LoginViewModelFactory
import com.healios.dreams.util.EventObserver
import com.healios.dreams.util.managers.hideKeyboard
import com.healios.dreams.util.ui.JumpTextWatcher
import kotlinx.android.synthetic.main.custom_view_digit_code_item.view.*


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
        setupView()
        return binding.root
    }

    private fun bind() {
        binding.textViewVerifyPhoneInfoMessageSentTo.text =
            HtmlCompat.fromHtml(getString(R.string.verifyPhone_sent_to, viewModel.phoneNumber.value),
                HtmlCompat.FROM_HTML_MODE_COMPACT)


        viewModel.code.observe(this) {
            if (it.length == 4) {
                hideKeyboard()
            }
        }

        viewModel.verifiedCodeEvent.observe(viewLifecycleOwner, EventObserver {

        })

    }

    private fun setupView() {
        val firstCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeFirstItem.editTextDigitCodeItemNumber
        val secondCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeSecondItem.editTextDigitCodeItemNumber
        val thirdCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeThirdItem.editTextDigitCodeItemNumber
        val forthCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeFourthItem.editTextDigitCodeItemNumber

        firstCode.addTextChangedListener(JumpTextWatcher(secondCode))
        secondCode.addTextChangedListener(JumpTextWatcher(thirdCode))
        thirdCode.addTextChangedListener(JumpTextWatcher(forthCode))
    }



}