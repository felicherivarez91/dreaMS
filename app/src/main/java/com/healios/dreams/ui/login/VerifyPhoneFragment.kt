package com.healios.dreams.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentVerifyPhoneBinding
import com.healios.dreams.util.EventObserver
import com.healios.dreams.util.managers.hideKeyboard
import com.healios.dreams.util.ui.JumpTextWatcher


class VerifyPhoneFragment : Fragment() {


    private lateinit var binding: FragmentVerifyPhoneBinding
    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(
            LoginViewModel::class.java)
    }

    private lateinit var firstCode: EditText
    private lateinit var secondCode: EditText
    private lateinit var thirdCode: EditText
    private lateinit var forthCode: EditText


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

    override fun onResume() {
        super.onResume()
        viewModel.setupVerifyCode()
    }

    private fun bind() {
        binding.textViewVerifyPhoneInfoMessageSentTo.text =
            HtmlCompat.fromHtml(getString(R.string.verifyPhone_sent_to, viewModel.fullPhonenumber.value),
                HtmlCompat.FROM_HTML_MODE_COMPACT)


        viewModel.code.observe(this) {}

        viewModel.startVerificationEvent.observe(this) {
            forthCode.clearFocus()
            hideKeyboard()
        }

        viewModel.verifiedCodeEvent.observe(viewLifecycleOwner, EventObserver {
            //TODO: Navigate to the next
        })

    }



    private fun setupView() {
        firstCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeFirstItem.editTextDigitCodeItemNumber
        secondCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeSecondItem.editTextDigitCodeItemNumber
        thirdCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeThirdItem.editTextDigitCodeItemNumber
        forthCode = binding.smsCodeViewVerifyPhoneInputCode.digitCodeFourthItem.editTextDigitCodeItemNumber

        firstCode.addTextChangedListener(JumpTextWatcher(secondCode))
        secondCode.addTextChangedListener(JumpTextWatcher(thirdCode))
        thirdCode.addTextChangedListener(JumpTextWatcher(forthCode))
        forthCode.addTextChangedListener(JumpTextWatcher(firstCode))
    }



}