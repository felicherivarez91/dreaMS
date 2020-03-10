package com.healios.dreams.ui.relapse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentRelapseLossVisionQuestionBinding

class RelapseLossVisionQuestionFragment : Fragment() {

    private lateinit var binding: FragmentRelapseLossVisionQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelapseLossVisionQuestionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.buttonLossVisionNo.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseLossVisionQuestionFragment_to_relapseLossMemoryQuestionFragment)
        }
        binding.buttonLossVisionYes.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseLossVisionQuestionFragment_to_tapAffectedEyesFragment)
        }
        return binding.root
    }

}
