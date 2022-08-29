package com.example.javadi.di.main;

import androidx.lifecycle.ViewModel;

import com.example.javadi.di.ViewModelKey;
import com.example.javadi.ui.main.post.PostFragmentviewModel;
import com.example.javadi.ui.main.profile.ProfileFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileFragmentViewModel.class)
    public abstract ViewModel bindProfileFragmentViewModel(ProfileFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostFragmentviewModel.class)
    public abstract ViewModel bindPostFragmentviewModel(PostFragmentviewModel viewModel);
}
