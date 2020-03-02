package com.healios.dreams.ui.dashboard.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R

class DashboardHomeViewPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {


    override fun getItem(position: Int): Fragment {
        return  when(position){
            0 -> DashboardChallengesCategoriesFragment.newInstance()
            1 -> DashboardNonCompletedChallengesFragment.newInstance()
            else -> DashboardChallengesCategoriesFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> DreaMSApp.instance.applicationContext.getString(R.string.fragment_dashboard_home_tab_categories_text).toUpperCase()
            1 -> DreaMSApp.instance.applicationContext.getString(R.string.fragment_dashboard_home_tab_non_completed_text).toUpperCase()
            else -> getPageTitle(0)
        }
    }


}