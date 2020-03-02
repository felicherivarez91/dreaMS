package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.schedule.ScheduleViewModel

@Suppress("UNCHECKED_CAST")
class ScheduleViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(InjectorUtils.getAccountInformationManager(), InjectorUtils.getTokenProvider(), InjectorUtils.getAccountInfoProvider()) as T
    }


}