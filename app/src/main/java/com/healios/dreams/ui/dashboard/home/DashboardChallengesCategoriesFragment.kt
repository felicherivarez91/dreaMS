package com.healios.dreams.ui.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.healios.dreams.R
import com.healios.dreams.databinding.FragmentDashboardChallengesCategoriesBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory
import com.healios.dreams.ui.dashboard.DashboardHomeViewModel

class DashboardChallengesCategoriesFragment : Fragment() {

    private lateinit var binding: FragmentDashboardChallengesCategoriesBinding

    private val viewModel by lazy {
        ViewModelProvider(activity!!, DashboardHomeViewModelFactory()).get(
            DashboardHomeViewModel::class.java
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardChallengesCategoriesBinding.inflate(inflater, container, false)
        binding.recyclerViewFragmentDashboardChallengesCategories.apply {
            val numberOfRows: Int = 2
            layoutManager = GridLayoutManager(context, numberOfRows, RecyclerView.VERTICAL, false)
            adapter = DashboardChallengesCategoriesRecyclerViewAdapter()
        }

        bind()
        return binding.root
    }

    private fun bind() {


    }







    companion object {
        @JvmStatic
        fun newInstance() = DashboardChallengesCategoriesFragment()
    }

}
