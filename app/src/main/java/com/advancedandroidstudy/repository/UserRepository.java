package com.advancedandroidstudy.repository;

import android.content.Context;

import com.advancedandroidstudy.DataBaseManager.DAO.UserDAO;
import com.advancedandroidstudy.DataBaseManager.DataBaseHelper;
import com.advancedandroidstudy.asyncTasks.BaseAsyncTask;
import com.advancedandroidstudy.callbacks.IDataLoadedCallBack;
import com.advancedandroidstudy.models.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserRepository {

    private final UserDAO userDAO;

    public UserRepository(Context context) {
        this.userDAO = DataBaseHelper.getInstance(context).getRoomUserDAO();
    }

    public LiveData<List<User>> getUserTable() {
        return userDAO.getUsers();
    }

    public void saveUser(final User model, final IDataLoadedCallBack<Long> callback) {
        new BaseAsyncTask<>(new BaseAsyncTask.ExecutaListener<Long>() {
            @Override
            public Long quandoExecuta() {
                return userDAO.saveUser(model);
            }
        }, new BaseAsyncTask.FinalizadaListener<Long>() {
            @Override
            public void quandoFinalizada(Long resultado) {
                callback.success(resultado);
            }
        }).execute();
    }

//    public LiveData<Resource<Long>> saveUser(final User model) {
//
//        final MutableLiveData<Resource<Long>> resourceMutableLiveData = new MutableLiveData<>();
//
//        new BaseAsyncTask<>(new BaseAsyncTask.ExecutaListener<Long>() {
//            @Override
//            public Long quandoExecuta() {
//                return userDAO.saveUser(model);
//            }
//        }, new BaseAsyncTask.FinalizadaListener<Long>() {
//            @Override
//            public void quandoFinalizada(Long resultado) {
//                resourceMutableLiveData.setValue(new Resource<Long>());
//            }
//        }).execute();
//
//        return resourceMutableLiveData;
//    }

    public void updateUser(final User model, final IDataLoadedCallBack<Void> callback) {
        new BaseAsyncTask<>(new BaseAsyncTask.ExecutaListener<Void>() {
            @Override
            public Void quandoExecuta() {
                userDAO.updateUser(model);
                return null;
            }
        }, new BaseAsyncTask.FinalizadaListener<Void>() {
            @Override
            public void quandoFinalizada(Void resultado) {
                callback.success(resultado);
            }
        }).execute();
    }

    public void deleteUser(final User model, final IDataLoadedCallBack<Void> callback) {

        new BaseAsyncTask<>(new BaseAsyncTask.ExecutaListener<Void>() {
            @Override
            public Void quandoExecuta() {
                userDAO.deleteUser(model);
                return null;
            }
        }, new BaseAsyncTask.FinalizadaListener<Void>() {
            @Override
            public void quandoFinalizada(Void resultado) {
                callback.success(resultado);
            }
        }).execute();
    }
}
