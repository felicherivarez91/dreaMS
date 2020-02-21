package com.healios.dreams.ui.quicktour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.healios.dreams.databinding.FragmentQuicktourStepBinding

class QuickTourFragment : Fragment() {

    companion object {
        fun newInstance() = QuickTourFragment()
    }

    private lateinit var viewModel: QuickTourViewModel
    private lateinit var binding: FragmentQuicktourStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuicktourStepBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this



        bind()
        return binding.root
    }

    private fun bind() {


    }


}
