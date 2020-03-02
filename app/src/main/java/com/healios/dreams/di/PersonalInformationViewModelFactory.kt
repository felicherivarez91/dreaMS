package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.account.PersonalInformationViewModel

@Suppress("UNCHECKED_CAST")
class PersonalInformationViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonalInformationViewModel(InjectorUtils.getAccountInformationManager(), InjectorUtils.getTokenProvider(), InjectorUtils.getAccountInfoProvider()) as T
    }


}