package com.dicoding.mylogin.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dicoding.mylogin.model.User;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_LOGGED_IN = "isLogginIn";
    public static final String ID_USER = "idUser";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String FULLNAME = "fullname";

    public Context getContext() {
        return context;
    }

    //constructor
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(User user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID_USER, user.getIdUser());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PHONE_NUMBER, user.getPhoneNumber());
        editor.putString(FULLNAME, user.getFullname());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String,String> user = new HashMap<>();
        user.put(ID_USER, sharedPreferences.getString(ID_USER,null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD,null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        user.put(PHONE_NUMBER, sharedPreferences.getString(PHONE_NUMBER,null));
        user.put(FULLNAME, sharedPreferences.getString(FULLNAME,null));
        return  user;

    }

    public void logOutUser() {
        //clearing all data from shared Preferences
        editor.clear();
        editor.commit();
    }

    public boolean isLogginIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
}