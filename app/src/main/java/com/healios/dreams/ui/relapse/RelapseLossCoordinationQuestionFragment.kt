package com.healios.dreams.ui.relapse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentRelapseLossCoordinationQuestionBinding

class RelapseLossCoordinationQuestionFragment : Fragment() {

    private lateinit var binding: FragmentRelapseLossCoordinationQuestionBinding

    companion object {
        fun newInstance() = RelapseLossCoordinationQuestionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelapseLossCoordinationQuestionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.buttonLossCoordinationYes.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseLossCoordinationQuestion_to_relapseLossVisionQuestionFragment)
        }
        return binding.root
    }

}
