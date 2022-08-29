package com.example.javadi.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.javadi.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory modelProviderFactory);
}
