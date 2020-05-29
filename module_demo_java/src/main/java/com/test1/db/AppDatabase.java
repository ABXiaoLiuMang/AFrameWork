package com.test1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.common.di.component.AppComponent;
import com.test1.db.entity.User;
import com.test1.db.entity.UserDao;


/**
 * 数据库
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public static AppDatabase get(AppComponent component) {
        return (AppDatabase) component.dbManager().database();
    }
}