package edu.ucsb.intbridge.derekxinzhewang.literatureparser.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Derek on 3/17/2016.
 */
public class SharedPreferencesHelper {
    private SharedPreferences sharedSettings;
    public SharedPreferencesHelper(Activity context){
        sharedSettings = context.getPreferences(context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedSettings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String error) {
        return sharedSettings.getString(key, error);
    }
}
