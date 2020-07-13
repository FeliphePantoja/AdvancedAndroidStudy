package com.advancedandroidstudy.DataBaseManager;

import android.content.Context;

import com.advancedandroidstudy.DataBaseManager.DAO.UserDAO;
import com.advancedandroidstudy.DataBaseManager.DAO.UserDAOWithoutLiveDate;
import com.advancedandroidstudy.models.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@TypeConverters({if converter classes exist})
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DataBaseHelper extends RoomDatabase {

    private static final String DATABASE_NAME = "androiddatabase.db";

    public abstract UserDAO getRoomUserDAO();

    public abstract UserDAOWithoutLiveDate getRoomUserDAOWithoutLiveDate();

    private static DataBaseHelper dataBaseHelper;

    public static DataBaseHelper getInstance(Context context) {

        if (dataBaseHelper != null) return dataBaseHelper;

        dataBaseHelper = Room
                .databaseBuilder(context, DataBaseHelper.class, DATABASE_NAME)
                .build();

        return dataBaseHelper;

        //.allowMainThreadQueries()
        //.addMigrations(ALL_MIGRATIONS)
    }

}
