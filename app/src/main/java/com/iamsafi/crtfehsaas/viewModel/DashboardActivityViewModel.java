package com.iamsafi.crtfehsaas.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.iamsafi.crtfehsaas.database.Person;
import com.iamsafi.crtfehsaas.repository.AppRepository;

import java.util.List;

public class DashboardActivityViewModel extends AndroidViewModel {
    public LiveData<List<Person>> personsList;
    private AppRepository mRepository;

    public DashboardActivityViewModel(Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        personsList = mRepository.personsList;
    }

    public void deletePeople(Person person) {
    mRepository.deletePeople(person);

    }
}
