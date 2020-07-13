package com.advancedandroidstudy.views.viewmodel.factory;

import com.advancedandroidstudy.repository.UserRepository;
import com.advancedandroidstudy.views.viewmodel.FormUserViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
/**
 * if you want to send other parameters to the constructor. That’s why separate factories are created
 * **/
public class FormUserViewModelFactory implements ViewModelProvider.Factory{
    private UserRepository userRepository;

    public FormUserViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormUserViewModel(userRepository);
    }
}