package ch.bullfin.multilanguagechat.config;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by root on 11/10/14.
 */
public class Configuration {

    private static final String PREF_CURRENT_LANG_KEY = "currentLang";
    private static final String PREF_FILE = "currentFile";
    private static Configuration instance = null;
    private String langName;
    private String langCode;

    public Configuration() {
        langCode = null;
        langName = null;
    }

    synchronized public static Configuration getInstance(Context context) {
        if (instance == null) {
            String configJson = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getString(PREF_CURRENT_LANG_KEY, null);
            try{
                instance = new Gson().fromJson(configJson, Configuration.class);
            } catch ( JsonSyntaxException e) {
                e.getStackTrace();
            }

            if (instance == null) {
                instance = new Configuration();
            }
        }
        return instance;
    }

    public void save(Context context) {
        String configJson = new Gson().toJson(this);
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit().putString(PREF_CURRENT_LANG_KEY, configJson).commit();
    }


    public String getCurrentLang(Context context){
        return langCode;
    }

    public void setCurrentLang(String selectedLang){
        langCode = selectedLang;
    }

}
