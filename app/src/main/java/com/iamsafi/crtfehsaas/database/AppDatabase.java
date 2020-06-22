package com.iamsafi.crtfehsaas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "register_rashan_people.db";
    static final Object obj = new Object();
    public static volatile AppDatabase instance;

    public abstract RegisterPeopleDao RegisterPeopleDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (obj) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
