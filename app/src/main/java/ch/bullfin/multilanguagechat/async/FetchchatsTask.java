package ch.bullfin.multilanguagechat.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.model.Chat;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by meera on 11/10/14.
 */
public class FetchchatsTask extends AsyncTask<Void, Void, Response> {
    private Context mContext;
    private FetchChatsCallback mCallback;

    public FetchchatsTask(Context context, FetchChatsCallback fetchChatsCallback) {
        mContext = context;
        mCallback = fetchChatsCallback;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        HashMap<String, String> apiParams = new HashMap<String, String>();
        return new HTTPManager("http://sandbox.bullfin.ch:9000/users/" + User.getInstance(mContext).getId() + "/chats.json").get();
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            Log.v("JSON", response.getResponseBody());
            Chat[] chat = new Gson().fromJson(response.getResponseBody(),Chat[].class);
            mCallback.onFetchchatsCompleted(chat);
        }
    }

    public interface FetchChatsCallback {
        public abstract void onFetchchatsCompleted(Chat[] chat);
        public abstract void onFetchchatsFailed();
    }
}
