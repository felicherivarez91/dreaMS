package com.healios.dreams.ui.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.healios.dreams.databinding.FragmentDashboardCategoryChallengesPathBinding
import com.healios.dreams.di.DashboardHomeViewModelFactory
import com.healios.dreams.model.Test
import com.healios.dreams.model.challenge.metadata.ChallengeCategory
import com.healios.dreams.ui.navigation.common.ChallengeViewListener
import com.healios.dreams.ui.navigation.common.DashboardCategoryPathView
import com.healios.dreams.util.hideBottomNavigationViewIfProceeds
import com.healios.dreams.util.showBottomNavigationViewIfProceeds

class DashboardCategoryChallengesPathFragment : Fragment(), ChallengeViewListener {

    companion object {
        fun newInstance() = DashboardCategoryChallengesPathFragment()
    }

    private val args: DashboardCategoryChallengesPathFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity!!, DashboardHomeViewModelFactory()).get(
            DashboardHomeViewModel::class.java
        )
    }

    private lateinit var binding: FragmentDashboardCategoryChallengesPathBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val categoryId = args.categoryId
        val selectedDay = args.selectedDay
        binding = FragmentDashboardCategoryChallengesPathBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        bind(categoryId)
        return binding.root
    }

    private fun bind(categoryId: Int) {

        val view = DashboardCategoryPathView(context, this)
        val challengesOfCategory = viewModel.getTestChallengesOfCategory(categoryId)
        challengesOfCategory?.let {
            view.listOfChallenges = it

        }
        val numOfChallengesCompleted = challengesOfCategory?.filter { it.completedAt != null }?.size ?: 0
        val challengeCategory = ChallengeCategory.values().first {
            it.ordinal + 1 == categoryId
        }

        binding.category = getString(challengeCategory.description);
        binding.numOfChallenges = challengesOfCategory?.size ?: 0
        binding.challengesCompleted = numOfChallengesCompleted

        binding.relativeLayoutFragmentDashboardCategoryChallengesPathContentLayout.addView(view)


        binding.toolbarChallengesPath.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

    }

    //region: Show && Hide Bottom Navigation Bar View
    override fun onResume() {
        super.onResume()
        hideBottomNavigationViewIfProceeds()
    }

    override fun onPause() {
        super.onPause()
        showBottomNavigationViewIfProceeds()
    }
    //endregion


    override fun onChallengeIconClick(test: Test?) {
        TODO("*** Not implemented yet. Challenges need to be defined ***")
    }
}
