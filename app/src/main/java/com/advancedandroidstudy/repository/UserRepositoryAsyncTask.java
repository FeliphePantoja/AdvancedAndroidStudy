package com.advancedandroidstudy.repository;

import android.content.Context;

import com.advancedandroidstudy.DataBaseManager.DAO.UserDAO;
import com.advancedandroidstudy.DataBaseManager.DAO.UserDAOWithoutLiveDate;
import com.advancedandroidstudy.DataBaseManager.DataBaseHelper;
import com.advancedandroidstudy.asyncTasks.BaseAsyncTask;
import com.advancedandroidstudy.callbacks.IDataLoadedCallBack;
import com.advancedandroidstudy.models.User;

import java.util.List;

public class UserRepositoryAsyncTask {

    private final UserDAOWithoutLiveDate userDAO;

    public UserRepositoryAsyncTask(Context context) {
        this.userDAO = DataBaseHelper.getInstance(context).getRoomUserDAOWithoutLiveDate();
    }

    public void getUserTable(final IDataLoadedCallBack<List<User>> callback) {

        new BaseAsyncTask<>(new BaseAsyncTask.ExecutaListener<List<User>>() {
            @Override
            public List<User> quandoExecuta() {
                return userDAO.getUsers();
            }
        }, new BaseAsyncTask.FinalizadaListener<List<User>>() {
            @Override
            public void quandoFinalizada(List<User> resultado) {
                callback.success(resultado);
            }
        }).execute();
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
