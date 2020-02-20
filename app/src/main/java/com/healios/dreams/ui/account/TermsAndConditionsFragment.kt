package com.healios.dreams.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.databinding.FragmentTermsandconditionsBinding
import com.healios.dreams.di.TermsAndConditionsViewModelFactory

class TermsAndConditionsFragment : Fragment() {

    companion object {
        fun newInstance() = TermsAndConditionsFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(activity!!, TermsAndConditionsViewModelFactory()).get(
            TermsAndConditionsViewModel::class.java)
    }

    private lateinit var binding: FragmentTermsandconditionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTermsandconditionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        bind()
        return binding.root
    }

    private fun bind() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
