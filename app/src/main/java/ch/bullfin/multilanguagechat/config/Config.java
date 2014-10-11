package ch.bullfin.multilanguagechat.config;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by root on 11/10/14.
 */
public class Config {

    private static final String PREF_FILE = "ConfigFile";
    private static final String PREF_KEY = "ConfigKey";

    private String langName;
    private String langCode;

    public Config() {
        langCode = "en";
        langName = "English";
    }

    private static Config instance = null;

    synchronized public static Config getInstance(Context context) {
        if (instance == null) {
            String configJson = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getString(PREF_KEY, null);
            try{
                instance = new Gson().fromJson(configJson, Config.class);
            } catch ( JsonSyntaxException e) {
                e.getStackTrace();
            }

            if (instance == null) {
                instance = new Config();
            }
        }
        return instance;
    }

    public void save(Context context) {
        String configJson = new Gson().toJson(this);
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit().putString(PREF_KEY, configJson).commit();
    }


    public String getLanguageCode(){
        return langCode;
    }

    public void setLanguageCode(String selectedLang){
        langCode = selectedLang;
    }

    public String getLanguageName() {
        return langName;
    }

    public void setLanguageName(String langName) {
        this.langName = langName;
    }
}
