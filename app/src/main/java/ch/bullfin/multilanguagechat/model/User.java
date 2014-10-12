package ch.bullfin.multilanguagechat.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;

import ch.bullfin.multilanguagechat.util.BFUtils;

/**
 * Created by root on 11/10/14.
 */
public class User implements Serializable {
    private static final String PREF_FILE = "UserFile";
    private static final String PREF_KEY = "UserKey";

    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String authentication_token;

    private static User mCurrentUser = null;

    public static synchronized User getInstance(Context context) {
        if(mCurrentUser == null) {
            mCurrentUser = load(context);
            if (mCurrentUser == null) {
                mCurrentUser = new User();
            }
        }
        return mCurrentUser;
    }

    private static User load(Context context) {
        User user = null;

        String userJson = BFUtils.loadFromSharedPreference(context, PREF_FILE, PREF_KEY);
        if(userJson != null) {
            Gson gson = new Gson();
            try {
                user = gson.fromJson(userJson, User.class);
            } catch (JsonParseException e) { }
        }
        return user;
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
