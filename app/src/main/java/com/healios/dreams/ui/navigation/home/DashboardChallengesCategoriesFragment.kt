package com.healios.dreams.ui.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healios.dreams.databinding.FragmentDashboardChallengesCategoriesBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory
import com.healios.dreams.model.challenge.metadata.ChallengeCategoryMetadata

interface DashboardChallengesCategoriesRecyclerViewListener {
    fun onItemClick(position: Int, category: ChallengeCategoryMetadata)
}

class DashboardChallengesCategoriesFragment : Fragment(), DashboardChallengesCategoriesRecyclerViewListener {

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
            adapter = DashboardChallengesCategoriesRecyclerViewAdapter(this@DashboardChallengesCategoriesFragment)
        }

        bind()
        return binding.root
    }

    private fun bind() {

        viewModel.challengeCategories.observe(viewLifecycleOwner, Observer {
            val adapter = binding.recyclerViewFragmentDashboardChallengesCategories.adapter
            if (adapter is DashboardChallengesCategoriesRecyclerViewAdapter) {
                adapter.setData(it)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardChallengesCategoriesFragment()
    }

    override fun onItemClick(position: Int, category: ChallengeCategoryMetadata) {
        viewModel.onCategoryStartButtonPressed(position, category)
    }

}
