package com.healios.dreams.ui.challenge.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.healios.dreams.R

class ChallengeSummary : Fragment() {

    companion object {
        fun newInstance() =
            ChallengeSummary()
    }

    //private lateinit var viewModel: ChallengeSummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenge_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ChallengeSummaryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
