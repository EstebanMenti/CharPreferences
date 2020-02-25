package com.example.charpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Preferences{

    public static final int MAIL = 772;
    public static final int PASSWORD = 435;

    private SharedPreferences prefs;

    private static final String spreferences="Preferences";
    private static final String smail="mail";
    private static final String spassword="password";

    private Context context;
    public Preferences(Context context) {
        this.context = context;
        prefs =  context.getSharedPreferences(spreferences,Context.MODE_PRIVATE);
    }

    public boolean setPref(int code, String value){
        switch (code){
            case MAIL:
                set(smail,value);
                return true;
            case PASSWORD:
                set(spassword,value);
                return true;
        }

        return false;
    }

    public String getPref(int code){
        switch (code){
            case MAIL:
                return get(smail);
            case PASSWORD:
                return get(spassword);
        }
        return "";
    }

    public void clearAll(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void clearValue(int code){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(getPref(code));
        editor.apply();
    }

    private void set(String code, String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(code,value);
        editor.apply();
    }

    private String get(String code){
        return prefs.getString(code,"");
    }

}
