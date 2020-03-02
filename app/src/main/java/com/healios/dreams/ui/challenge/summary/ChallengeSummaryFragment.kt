package com.healios.dreams.ui.challenge.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.healios.dreams.databinding.FragmentChallengeSummaryBinding
import com.healios.dreams.di.ChallengeViewModelFactory
import com.healios.dreams.model.challenge.runtime.ChallengeInstanceData

class ChallengeSummaryFragment : Fragment() {

    private val viewModel: ChallengeSummaryViewModel by lazy {
        ViewModelProvider(activity!!, ChallengeViewModelFactory(workingData)).get(
            ChallengeSummaryViewModel::class.java
        )
    }

    private lateinit var binding: FragmentChallengeSummaryBinding
    private lateinit var workingData: ChallengeInstanceData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeSummaryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }


}
