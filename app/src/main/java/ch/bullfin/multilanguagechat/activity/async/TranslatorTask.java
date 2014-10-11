package ch.bullfin.multilanguagechat.activity.async;

import android.os.AsyncTask;
import android.util.Log;

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

    public TranslatorTask(String source, String target, String q) {
        this.source = source;
        this.target = target;
        this.q = q;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        HashMap<String, String> apiParams = new HashMap<String, String>();
        apiParams.put("key", "");
        apiParams.put("source", source);
        apiParams.put("target", target);
        apiParams.put("q", q);
        return new HTTPManager(BASE_URL).get(apiParams);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            Log.v("JSON", response.getResponseBody());
        }
    }

    public interface TranslationCallback {
        public abstract void onTranslationCompleted(String message);
        public abstract void onTranslationFailed();
    }
}
