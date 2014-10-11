package ch.bullfin.multilanguagechat.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by root on 11/10/14.
 */
public class User {
    private static final String PREF_FILE = "UserFile";
    private static final String PREF_KEY = "UserKey";

    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String authentication_token;

    private static User instance = null;

    public static User getInstance(Context context) {
        if (instance == null) {
            String json = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
                    .getString(PREF_KEY, null);
            try {
                instance = new Gson().fromJson(json, User.class);
            } catch (JsonSyntaxException e) {
                e.getStackTrace();
            }

            if (instance == null) {
                instance = new User();
            }
        }

        return instance;
    }

    public void save(Context context) {
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_KEY, new Gson().toJson(this))
                .commit();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthenticationToken() {
        return authentication_token;
    }

    public void setAuthenticationToken(String authentication_tocken) {
        this.authentication_token = authentication_tocken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
