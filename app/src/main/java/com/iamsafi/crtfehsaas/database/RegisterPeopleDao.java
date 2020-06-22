package com.iamsafi.crtfehsaas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegisterPeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM Register_People WHERE cnic=:cnic")
    List<Person> getPersonById(String cnic);

    @Query("SELECT * FROM Register_People ORDER BY id DESC")
    LiveData<List<Person>> getAllPeople();

    @Query("SELECT * FROM Register_People WHERE Registration_Status=:status")
    List<Person> getPeoplewithStatus(String status);

    @Query("SELECT COUNT(*) FROM Register_People")
    int getCountPeople();
}
