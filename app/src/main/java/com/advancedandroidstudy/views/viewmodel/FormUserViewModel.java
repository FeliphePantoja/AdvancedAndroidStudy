package com.advancedandroidstudy.views.viewmodel;

import com.advancedandroidstudy.callbacks.IDataLoadedCallBack;
import com.advancedandroidstudy.models.User;
import com.advancedandroidstudy.repository.Resource;
import com.advancedandroidstudy.repository.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormUserViewModel extends ViewModel {

    private UserRepository userRepository;

    public FormUserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<Resource<Long>> saveUser(User user) {

        final MutableLiveData<Resource<Long>> resourceMutableLiveData = new MutableLiveData<>();
        final Resource<Long> value = new Resource<>();

        this.userRepository.saveUser(user, new IDataLoadedCallBack<Long>() {
            @Override
            public void success(Long result) {

                value.success = result;
                resourceMutableLiveData.setValue(value);
            }

            @Override
            public void failure(Long result) {
                value.failure = "ERROR";
                resourceMutableLiveData.setValue(value);
            }
        });

        return resourceMutableLiveData;
    }

    public LiveData<Resource<Void>> editUser(User user) {
        final MutableLiveData<Resource<Void>> resourceMutableLiveData = new MutableLiveData<>();
        final Resource<Void> value = new Resource<>();

        this.userRepository.updateUser(user, new IDataLoadedCallBack<Void>() {
            @Override
            public void success(Void result) {
                resourceMutableLiveData.setValue(new Resource<Void>());
            }

            @Override
            public void failure(Void result) {
                value.failure = "ERROR";
                resourceMutableLiveData.setValue(value);
            }
        });

        return resourceMutableLiveData;
    }

    public LiveData<Resource<Void>> deleteUser(User user) {
        final MutableLiveData<Resource<Void>> resourceMutableLiveData = new MutableLiveData<>();
        final Resource<Void> value = new Resource<>();

        this.userRepository.deleteUser(user, new IDataLoadedCallBack<Void>() {
            @Override
            public void success(Void result) {
                resourceMutableLiveData.setValue(new Resource<Void>());
            }

            @Override
            public void failure(Void result) {
                value.failure = "ERROR";
                resourceMutableLiveData.setValue(value);
            }
        });

        return resourceMutableLiveData;
    }
}
