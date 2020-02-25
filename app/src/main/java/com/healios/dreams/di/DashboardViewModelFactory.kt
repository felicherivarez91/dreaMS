package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.dashboard.DashboardViewModel

@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(InjectorUtils.getUserManager(),
            InjectorUtils.getTokenProvider(),
            InjectorUtils.getUserCollectionDataRepository(),
            InjectorUtils.getUserPreferences()) as T
    }


}