package com.healios.dreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healios.dreams.ui.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(InjectorUtils.getLoginManager()) as T
    }


}