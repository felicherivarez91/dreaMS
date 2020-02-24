package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.quicktour.QuickTourViewModel

@Suppress("UNCHECKED_CAST")
class QuickTourViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuickTourViewModel(InjectorUtils.getAccountInformationManager(), InjectorUtils.getTokenProvider()) as T
    }


}