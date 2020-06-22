package com.iamsafi.crtfehsaas.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.iamsafi.crtfehsaas.database.Person;
import com.iamsafi.crtfehsaas.repository.AppRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FormActivityViewModel extends AndroidViewModel {
    AppRepository appRepository;
    Executor executor = Executors.newSingleThreadExecutor();

    public FormActivityViewModel(Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void addPeople(Person person) {
        appRepository.insertPerson(person);
    }
}
