package com.healios.dreams.ui.account

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentPersonalinformationBinding
import com.healios.dreams.databinding.FragmentSelectavatarBinding

class SelectAvatarFragment : Fragment() {

    companion object {
        fun newInstance() = SelectAvatarFragment()
    }

    private lateinit var binding: FragmentSelectavatarBinding
    //private lateinit var viewModel: SelectAvatarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectavatarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        //binding.viewmodel = viewModel

        

        bind()
        return binding.root
    }


    private fun bind() {


    }

}
