package com.example.educationnotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    // Shared preferences file name
    private static final String PREF_NAME = "EducationNotesPref";

    // Shared preferences keys
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_NAME = "userName";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Method to set login status
    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    // Method to check login status
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Method to set user name
    public void setUserName(String userName) {
        editor.putString(KEY_USER_NAME, userName);
        editor.apply();
    }

    // Method to get user name
    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }

    // Method to logout user
    public void logoutUser() {
        // Clear all data from shared preferences and redirect to login activity
        editor.clear();
        editor.apply();
        Intent loginIntent = new Intent(context, logInActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(loginIntent);
    }
}
