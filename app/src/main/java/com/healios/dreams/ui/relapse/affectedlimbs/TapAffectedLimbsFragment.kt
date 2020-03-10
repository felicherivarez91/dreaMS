package com.healios.dreams.ui.relapse.affectedlimbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.healios.dreams.databinding.FragmentTapAffectedLimbsBinding

class TapAffectedLimbsFragment : Fragment() {

    private lateinit var binding: FragmentTapAffectedLimbsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTapAffectedLimbsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

}
