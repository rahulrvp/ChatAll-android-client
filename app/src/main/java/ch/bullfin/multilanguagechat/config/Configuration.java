package ch.bullfin.multilanguagechat.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 11/10/14.
 */
public class Configuration {

    private static final String CURRENT_LANG_KEY = "currentLang";

    public static String getCurrentLang(Context context){
        SharedPreferences currentLang = context.getSharedPreferences(CURRENT_LANG_KEY,Context.MODE_PRIVATE);
        return currentLang.getString(CURRENT_LANG_KEY,"");
    }

    public static void setCurrentLang(Context context,String selectedLang){
        SharedPreferences currentLang = context.getSharedPreferences(CURRENT_LANG_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = currentLang.edit();
        editor.putString(CURRENT_LANG_KEY,selectedLang);
        editor.commit();
    }
}