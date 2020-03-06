package com.healios.dreams.ui.relapse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentRelapseReportBinding

class RelapseReportFragment : Fragment() {

    private lateinit var binding: FragmentRelapseReportBinding

    companion object {
        fun newInstance() = RelapseReportFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelapseReportBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.reportRelapseButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_relapseReportFragment_to_relapseLossStrengthQuestionFragment)
        }
        return binding.root
    }

}
