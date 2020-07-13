package com.advancedandroidstudy.views.viewmodel.factory;

import android.annotation.SuppressLint;
import android.app.Application;

import com.advancedandroidstudy.repository.UserRepository;
import com.advancedandroidstudy.views.viewmodel.ListUsersViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListUserViewModelFactory implements ViewModelProvider.Factory {

    private UserRepository userRepository;

    public ListUserViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListUsersViewModel(userRepository);
    }
}
