package com.example.javadi.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.javadi.di.ViewModelKey;
import com.example.javadi.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
