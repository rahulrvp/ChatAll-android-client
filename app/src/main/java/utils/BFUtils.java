package utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.Editable;
import android.widget.EditText;

import java.util.List;

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

    public static boolean isValidEmailAddress(String emailID) {
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


    public static ComponentName getTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

            if (taskInfo != null) {
                return taskInfo.get(0).topActivity;
            }
        }

        return null;
    }
}
