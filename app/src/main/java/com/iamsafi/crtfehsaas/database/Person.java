package com.iamsafi.crtfehsaas.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Register_People")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Registration_Status;
    private String Full_Name;
    private String Area;
    private String City;
    private String Zilla;
    private String Tehsil;
    private String CNIC;
    private String Mobile;
    private String CNIC_Issue_Date;
    private String Martial_Status;
    private String Gender;
    private String Flat_No;

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCNIC_Issue_Date() {
        return CNIC_Issue_Date;
    }

    public void setCNIC_Issue_Date(String CNIC_Issue_Date) {
        this.CNIC_Issue_Date = CNIC_Issue_Date;
    }

    public String getMartial_Status() {
        return Martial_Status;
    }

    public void setMartial_Status(String martial_Status) {
        Martial_Status = martial_Status;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFlat_No() {
        return Flat_No;
    }

    public void setFlat_No(String flat_No) {
        Flat_No = flat_No;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZilla() {
        return Zilla;
    }

    public void setZilla(String zilla) {
        Zilla = zilla;
    }

    public String getTehsil() {
        return Tehsil;
    }

    public void setTehsil(String tehsil) {
        Tehsil = tehsil;
    }

    public String getRegistration_Status() {
        return Registration_Status;
    }

    public void setRegistration_Status(String registration_Status) {
        Registration_Status = registration_Status;
    }
}
