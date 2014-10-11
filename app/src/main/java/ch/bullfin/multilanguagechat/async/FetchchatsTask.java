package ch.bullfin.multilanguagechat.async;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;

/**
 * Created by meera on 11/10/14.
 */
public class FetchchatsTask extends AsyncTask<Void, Void, Response> {
    private static final String BASE_URL = "";

    public FetchchatsTask() {

    }

    @Override
    protected Response doInBackground(Void... voids) {
        HashMap<String, String> apiParams = new HashMap<String, String>();

        return new HTTPManager(BASE_URL).get(apiParams);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            Log.v("JSON", response.getResponseBody());
            try {
                JSONObject jsonObject = new JSONObject(response.getResponseBody());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface FetchChatsCallback {
        public abstract void onFetchchatsCompleted(String message);
        public abstract void onFetchchatsFailed();
    }
}
