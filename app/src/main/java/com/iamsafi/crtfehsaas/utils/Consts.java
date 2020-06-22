package com.iamsafi.crtfehsaas.utils;

import android.content.Context;
import android.os.Environment;
import android.content.SharedPreferences;
import android.util.Log;


@SuppressWarnings("deprecation")
public class Consts {
    public static final String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Tiger Force/";

    private SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    private String prefs_file_name = "register_data";
    private String prefs_flat_no = "Flat_no";
    private String prefs_mohallah = "Mohallah";
    private String prefs_place_name = "Place Name";

    public Consts(Context context) {
        this.sharedpreferences = context.getSharedPreferences(prefs_file_name, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setPlaceData(String flat_no, String mohallah, String place_name) {
        Log.i("check","Flat:"+flat_no+ "Mohallah "+mohallah+"Place Name"+place_name);
        if (!flat_no.trim().equalsIgnoreCase(""))
            editor.putString(prefs_flat_no, flat_no);
        if (!mohallah.trim().equalsIgnoreCase(""))
        editor.putString(prefs_mohallah, mohallah);
        if (!place_name.trim().equalsIgnoreCase(""))
        editor.putString(prefs_place_name, place_name);
        editor.commit();
    }

    public String getFlatNo() {
        return sharedpreferences.getString(prefs_flat_no, "");
    }

    public String getMohallah() {
        return sharedpreferences.getString(prefs_mohallah, "");
    }

    public String getPlace() {
        return sharedpreferences.getString(prefs_place_name, "");
    }
}
