package com.healios.dreams.ui.relapse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentRelapseLossStrengthQuestionBinding

class RelapseLossStrengthQuestionFragment : Fragment() {

    private lateinit var binding: FragmentRelapseLossStrengthQuestionBinding

    companion object {
        fun newInstance() = RelapseLossStrengthQuestionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelapseLossStrengthQuestionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.buttonLossStrengthNo.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseLossStrengthQuestionFragment_to_relapseLossCoordinationQuestion)
        }
        binding.buttonLossStrengthYes.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseLossStrengthQuestionFragment_to_tapAffectedLimbsFragment)
        }
        return binding.root
    }

}
