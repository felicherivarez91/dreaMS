package com.healios.dreams.ui.relapse.affectedeyes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.databinding.FragmentTapAffectedEyesBinding

class TapAffectedEyesFragment : Fragment() {

    private lateinit var binding: FragmentTapAffectedEyesBinding

    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(TapAffectedEyesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTapAffectedEyesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }
}
