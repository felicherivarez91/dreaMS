package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.account.TermsAndConditionsViewModel

@Suppress("UNCHECKED_CAST")
class TermsAndConditionsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TermsAndConditionsViewModel() as T
    }


}