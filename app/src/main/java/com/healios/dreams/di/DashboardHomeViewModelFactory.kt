package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.dashboard.DashboardHomeViewModel

@Suppress("UNCHECKED_CAST")
class DashboardHomeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardHomeViewModel(InjectorUtils.getUserManager(),
            InjectorUtils.getTokenProvider(),
            InjectorUtils.getUserCollectionDataRepository(),
            InjectorUtils.getUserPreferences()) as T
    }


}