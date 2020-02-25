package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.account.SelectAvatarViewModel

@Suppress("UNCHECKED_CAST")
class SelectAvatarViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectAvatarViewModel() as T
    }


}