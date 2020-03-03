package com.healios.dreams.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.healios.dreams.databinding.FragmentDashboardCategoryChallengesPathBinding
import com.healios.dreams.ui.dashboard.common.DashboardCategoryPathView

class DashboardCategoryChallengesPathFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardCategoryChallengesPathFragment()
    }

    private val args: DashboardCategoryChallengesPathFragmentArgs by navArgs()


    //private lateinit var viewModel: DashboardCategoryChallengesPathViewModel

    private lateinit var binding: FragmentDashboardCategoryChallengesPathBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val categoryId = args.categoryId
        binding = FragmentDashboardCategoryChallengesPathBinding.inflate(inflater, container, false)

        bind()
        return binding.root
    }

    private fun bind() {

        val view = DashboardCategoryPathView(context)
        binding.relativeLayoutFragmentDashboardCategoryChallengesPathContentLayout.addView(view)

        binding.toolbarChallengesPath.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

}
