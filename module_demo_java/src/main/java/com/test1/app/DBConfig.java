package com.test1.app;

import android.content.Context;

import androidx.room.RoomDatabase;

import com.common.db.DatabaseConfig;
import com.common.di.module.DBModule;
import com.common.utils.AppUtils;
import com.common.utils.ProjectUtils;
import com.test1.db.AppDatabase;

import timber.log.Timber;

/**
 * 扩展自定义配置数据库参数
 */
public class DBConfig implements DBModule.DBConfiguration {

    @SuppressWarnings("unchecked")
    @Override
    public void configDB(Context context, DatabaseConfig.Builder builder) {
        builder
                .path(ProjectUtils.ROOT_PATH)
                .name(AppUtils.getVersionName(context)+".db")
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .databaseClass(AppDatabase.class)
                .allowMainThreadQueries();
    }

    @Override
    public void createdDB(Context context, RoomDatabase database) {
        Timber.e(database.getOpenHelper().getDatabaseName());
    }
}