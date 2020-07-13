package com.advancedandroidstudy.DataBaseManager.DAO;

import com.advancedandroidstudy.models.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Query("SELECT *FROM User")
    LiveData<List<User>> getUsers();

    @Query("SELECT *FROM User WHERE id =:idUser")
    User getUserForId(int idUser);

    @Insert
    Long saveUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
