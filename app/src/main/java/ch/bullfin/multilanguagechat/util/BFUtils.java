package ch.bullfin.multilanguagechat.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.text.Editable;
import android.widget.EditText;

/**
 * Utility file to write commonly used functions
 *
 * @author Asif CH
 * Copyright Bullfinch Software Pvt Ltd @ 2014
 */
public class BFUtils {

    public static long SECOND = 1000;
    public static long MINUTE = 60 * SECOND;
    public static long HOUR = 60 * MINUTE;

    public static String getStringFromEditable(EditText editText){
        String value = null;
        if (editText != null) {
            Editable editable = editText.getText();
            if (editable != null) {
                value = editable.toString();
                if(value.equals("")){
                    value = null;
                }
            }
        }

        return value;
    }

    public static boolean isVaildEmailAddress(String emailID) {
        if(emailID != null) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailID).matches();
        } else {
            return false;
        }
    }

    public static void setError(EditText editText, String errorMessage) {
        if (editText != null) {
            editText.setError(errorMessage);
        }
    }

    public static String getDurationStringFromMillis(long durationInMillis) {
        if (durationInMillis < SECOND) {
            return "" + durationInMillis + " ms";
        } else if (durationInMillis < MINUTE) {
            return "" + (int)(durationInMillis / SECOND) + " sec";
        } else if (durationInMillis < HOUR) {
            return "" + (int)(durationInMillis / MINUTE) + ":" + (int) (durationInMillis / SECOND) % 60 + " min";
        } else {
            return "" + (int)(durationInMillis / HOUR) + ":" + (int) (durationInMillis / MINUTE) % 60 + " hr";
        }
    }

    public static String getDistanceString(long distanceInMeter) {
        if (distanceInMeter < 1000) {
            return "" + distanceInMeter + " M";
        } else if (distanceInMeter % 1000 == 0) {
            return "" + (int)(distanceInMeter / 1000) + " Km";
        } else {
            return "" + (int)(distanceInMeter / 1000) + "." + (int)(distanceInMeter % 1000 / 100) + " Km";
        }
    }

    public static void saveInPreferenceStore(Context context, String fileName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static String loadFromSharedPreference(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static Typeface getRobotoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
    }

    public static Typeface getRobotoBoldCondensed(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldCondensed.ttf");
    }

    public static Typeface getRobotoThin(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
    }

    public static Typeface getRobotoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
    }

    public static Typeface getRobotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
    }

}
