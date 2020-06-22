package com.iamsafi.crtfehsaas.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.iamsafi.crtfehsaas.database.AppDatabase;
import com.iamsafi.crtfehsaas.database.Person;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository mInstance;
    public LiveData<List<Person>> personsList;
    private AppDatabase mDatabase;
    Executor executor = Executors.newSingleThreadExecutor();

    private AppRepository(Context context) {
        mDatabase = AppDatabase.getInstance(context);
        personsList = getAllPersons();
    }

    private LiveData<List<Person>> getAllPersons() {
        return mDatabase.RegisterPeopleDao().getAllPeople();
    }

    public static AppRepository getInstance(Context context) {
        mInstance = new AppRepository(context);
        return mInstance;
    }

    public void insertPerson(Person person) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                 mDatabase.RegisterPeopleDao().insert(person);
                Log.i("check","Number of People= "+mDatabase.RegisterPeopleDao().getCountPeople());

            }
        });

    }

    public void deletePeople(Person person) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.RegisterPeopleDao().delete(person);
            }
        });
    }
}
