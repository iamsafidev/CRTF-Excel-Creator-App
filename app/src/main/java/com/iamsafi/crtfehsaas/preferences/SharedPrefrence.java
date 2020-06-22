package com.iamsafi.crtfehsaas.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



import java.lang.reflect.Type;

public class SharedPrefrence {
    public static SharedPreferences myPrefs;
    public static SharedPreferences.Editor prefsEditor;
    public static SharedPrefrence myObj;

    private SharedPrefrence() {

    }

    public void clearAllPreferences() {
        prefsEditor = myPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public static SharedPrefrence getInstance(Context ctx) {
        if (myObj == null) {
            myObj = new SharedPrefrence();
            myPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            prefsEditor = myPrefs.edit();
        }
        return myObj;
    }

    public void clearPreferences(String key) {
        prefsEditor.remove(key);
        prefsEditor.commit();
    }


    public void setIntValue(String Tag, int value) {
        prefsEditor.putInt(Tag, value);
        prefsEditor.apply();
    }

    public int getIntValue(String Tag) {
        return myPrefs.getInt(Tag, 0);

    }

    public void setLongValue(String Tag, long value) {
        prefsEditor.putLong(Tag, value);
        prefsEditor.apply();
    }

    public long getLongValue(String Tag) {
        return myPrefs.getLong(Tag, 0);

    }


    public void setValue(String Tag, String token) {
        prefsEditor.putString(Tag, token);
        prefsEditor.commit();
    }


    public String getValue(String Tag) {
        return myPrefs.getString(Tag, "default");
    }

    public boolean getBooleanValue(String Tag) {
        return myPrefs.getBoolean(Tag, false);

    }

    public void setBooleanValue(String Tag, boolean token) {
        prefsEditor.putBoolean(Tag, token);
        prefsEditor.commit();
    }

/*    public void setUserDTO(ModelSeekerLogin userSeekerDTO, String tag) {

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(userSeekerDTO);

        prefsEditor.putString(tag, hashMapString);
        prefsEditor.apply();
    }

    public ModelSeekerLogin getUserDTO(String tag) {
        String obj = myPrefs.getString(tag, "defValue");
        if (obj.equals("defValue")) {
            return new ModelSeekerLogin();
        } else {

            Gson gson = new Gson();
            String storedHashMapString = myPrefs.getString(tag, "");
            Type type = new TypeToken<ModelSeekerLogin>() {}.getType();
            ModelSeekerLogin testHashMap = gson.fromJson(storedHashMapString, type);

            return testHashMap;
        }
    }

 public void setLanguage(ModelLanguageDTO languageDTO, String tag) {

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(languageDTO);

        prefsEditor.putString(tag, hashMapString);
        prefsEditor.apply();
    }

    public ModelLanguageDTO getLanguage(String tag) {
        String obj = myPrefs.getString(tag, "defValue");
        if (obj.equals("defValue")) {
            return new ModelLanguageDTO();
        } else {

            Gson gson = new Gson();
            String storedHashMapString = myPrefs.getString(tag, "");
            Type type = new TypeToken<ModelLanguageDTO>() {}.getType();
            ModelLanguageDTO testHashMap = gson.fromJson(storedHashMapString, type);
            return testHashMap;
        }
    }




    public void setActiveJobsDTO(ActiveJobDTO activeJobDTO, String tag) {

        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(activeJobDTO);

        prefsEditor.putString(tag, hashMapString);
        prefsEditor.apply();
    }

    public ActiveJobDTO getActiveJobsDTO(String tag) {
        String obj = myPrefs.getString(tag, "defValue");
        if (obj.equals("defValue")) {
            return new ActiveJobDTO();
        } else {

            Gson gson = new Gson();
            String storedHashMapString = myPrefs.getString(tag, "");
            Type type = new TypeToken<ActiveJobDTO>() {
            }.getType();
            ActiveJobDTO testHashMap = gson.fromJson(storedHashMapString, type);

            return testHashMap;
        }
    }*/





}
