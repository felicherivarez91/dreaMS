package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.navigation.home.DashboardHomeViewModel

@Suppress("UNCHECKED_CAST")
class DashboardHomeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardHomeViewModel(InjectorUtils.getUserManager(),
            InjectorUtils.getTokenProvider(),
            InjectorUtils.getPatientRepository(),
            InjectorUtils.getUserPreferences()) as T
    }


}