package com.healios.dreams.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.healios.dreams.databinding.FragmentDashboardNonCompletedChallengesBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory

class DashboardNonCompletedChallengesFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(activity!!, DashboardHomeViewModelFactory()).get(
            DashboardHomeViewModel::class.java
        )
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

        viewModel.dailyNonCompletedChallenges.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewDashboardNonCompletedChallenges.adapter
            if (adapter is DashboardNonCompletedChallengesRecyclerViewAdapter) {
                adapter.setData(it)
            }
        })
    }

    companion object {
        fun newInstance() = DashboardNonCompletedChallengesFragment()
    }

}
