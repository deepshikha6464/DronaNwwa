package com.deepshikha.nwwa.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    //shared pref mode
    int PRIVATE_MODE = 0;

    //key name
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Email address (make variable public to access from outside)
    public static final String IMAGE_URL = "image_url";
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String IS_AUTHORIZED = "isUserAuthorized";

    public SharedPref (Context context) {
        this.context = context;
        pref = context.getSharedPreferences(KEY_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(String name,String email, String url, Boolean login)
    {
        editor.putBoolean(IS_LOGIN,login);
        editor.putString(KEY_NAME ,name);
        editor.putString(KEY_EMAIL ,email);
        editor.putString(IMAGE_URL ,url);

        //commit changes

        editor.commit();
    }
     public void setUserAuthorization(Boolean status){
         editor.putBoolean(IS_AUTHORIZED,status);
         editor.commit();
     }

     public boolean getUserAuthorization(){
        return pref.getBoolean(IS_AUTHORIZED,false);
     }
}
