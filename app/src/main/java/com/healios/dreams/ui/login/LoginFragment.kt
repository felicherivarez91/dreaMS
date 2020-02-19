package com.healios.dreams.ui.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentLoginBinding
import com.healios.dreams.di.LoginViewModelFactory
import com.healios.dreams.model.CountryModel
import com.healios.dreams.util.EventObserver
import com.healios.dreams.util.MaskWatcher
import com.healios.dreams.util.managers.hideKeyboard

class LoginFragment : Fragment(), CountrySelectorRecyclerViewListener, View.OnFocusChangeListener {


    private val viewModel by lazy {
        ViewModelProvider(activity!!, LoginViewModelFactory()).get(
            LoginViewModel::class.java
        )
    }

    private lateinit var binding: FragmentLoginBinding

    private lateinit var maskWatcher: MaskWatcher

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        maskWatcher = MaskWatcher(viewModel)

        binding.editTextLoginPhoneNumber.addTextChangedListener(maskWatcher)
        binding.editTextLoginPhoneNumber.onFocusChangeListener = this
        binding.recyclerViewBottomPicker.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CountrySelectorRecyclerViewAdapter(this@LoginFragment)
        }

        bind()
        return binding.root
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (v != null && v == binding.editTextLoginPhoneNumber) {
            viewModel.onPhoneNumberTextFocusChange(hasFocus)
        }
    }

    override fun onItemClick(itemView: View?, position: Int) {
        binding.editTextLoginPhoneNumber.setText("")
        viewModel.onCountrySelected(position)

        val countryModel = viewModel.countriesList.value?.get(position)
        maskWatcher.setCountryModel(countryModel)
    }

    private fun bind() {
        viewModel.acceptedPhoneEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_loginFragment_to_verifyPhoneFragment)
        })
    }

    class LoginBindingAdapter {
        companion object {
            @BindingAdapter("data")
            @JvmStatic
            fun setRecyclerViewProperties(
                recyclerView: RecyclerView?,
                data: MutableList<CountryModel>?
            ) {
                val adapter = recyclerView?.adapter
                if (adapter is CountrySelectorRecyclerViewAdapter && data != null) {
                    adapter.setData(data)
                }
            }
        }
    }

}