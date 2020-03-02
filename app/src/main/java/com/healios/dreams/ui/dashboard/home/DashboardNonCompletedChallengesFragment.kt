package com.healios.dreams.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.healios.dreams.databinding.FragmentDashboardNonCompletedChallengesBinding
import com.healios.dreams.ui.login.CountrySelectorRecyclerViewAdapter

class DashboardNonCompletedChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardNonCompletedChallengesFragment()
    }

    private lateinit var binding: FragmentDashboardNonCompletedChallengesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardNonCompletedChallengesBinding.inflate(inflater, container, false)
        binding.recyclerViewDashboardNonCompletedChallenges.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DashboardNonCompletedChallengesRecyclerViewAdapter()
        }

        bind()
        return binding.root

    }

    private fun bind() {

    }

}
