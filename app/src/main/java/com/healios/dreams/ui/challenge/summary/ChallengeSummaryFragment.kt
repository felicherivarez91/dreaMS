package com.healios.dreams.ui.challenge.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentChallengeSummaryBinding
import com.healios.dreams.di.ChallengeViewModelFactory
import com.healios.dreams.model.challenge.ChallengeWorkingData

class ChallengeSummaryFragment : Fragment() {

    private lateinit var binding: FragmentChallengeSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeSummaryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ChallengeSummaryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
