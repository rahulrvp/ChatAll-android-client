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
 * Created by root on 11/10/14.
 */
public class TranslatorTask extends AsyncTask<Void, Void, Response> {
    private static final String BASE_URL = "https://www.googleapis.com/language/translate/v2";

    private String source;
    private String target;
    private String q;
    private TranslationCallback callback;

    public TranslatorTask(String source, String target, String q, TranslationCallback callback) {
        this.source = source;
        this.target = target;
        this.q = q;
        this.callback = callback;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        HashMap<String, String> apiParams = new HashMap<String, String>();
        apiParams.put("key", "AIzaSyAbHbWqKuRf_ggp2WGlp7p7kqG3Sx58RDc");
        apiParams.put("source", source);
        apiParams.put("target", target);
        apiParams.put("q", q.replace(" ", "%20"));
        return new HTTPManager(BASE_URL).get(apiParams);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            Log.v("JSON", response.getResponseBody());
            try {
                JSONObject jsonObject = new JSONObject(response.getResponseBody());
                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("data"));
                JSONArray jsonArray = jsonObject1.getJSONArray("translations");
                callback.onTranslationCompleted(new JSONObject(jsonArray.get(0).toString()).getString("translatedText"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface TranslationCallback {
        public abstract void onTranslationCompleted(String message);
        public abstract void onTranslationFailed();
    }
}
