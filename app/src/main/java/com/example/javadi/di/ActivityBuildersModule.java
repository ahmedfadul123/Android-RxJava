package com.example.javadi.di;

import com.example.javadi.di.auth.AuthModule;
import com.example.javadi.di.auth.AuthViewModelModule;
import com.example.javadi.di.main.MainFragmentBuliderModule;
import com.example.javadi.di.main.MainModule;
import com.example.javadi.di.main.MainViewModelsModule;
import com.example.javadi.ui.auth.AuthActivity;
import com.example.javadi.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {
            AuthViewModelModule.class,
            AuthModule.class,
    })
    abstract AuthActivity ContributeAuthActivity();

    @ContributesAndroidInjector(modules = {
            MainFragmentBuliderModule.class, MainViewModelsModule.class, MainModule.class,
    })
    abstract MainActivity ContributeMainActivity();


}
