package com.advancedandroidstudy.views.viewmodel;

import com.advancedandroidstudy.models.User;
import com.advancedandroidstudy.repository.UserRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ListUsersViewModel extends ViewModel {

    private UserRepository userRepository;

    public ListUsersViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<User>> getUserTable() {
        return this.userRepository.getUserTable();
    }
}
