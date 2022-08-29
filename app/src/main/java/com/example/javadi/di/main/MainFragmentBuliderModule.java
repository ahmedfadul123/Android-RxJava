package com.example.javadi.di.main;

import com.example.javadi.ui.main.post.PostFragment;
import com.example.javadi.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuliderModule {

    @ContributesAndroidInjector
     abstract ProfileFragment ContributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment ContributePostFragment();
}
