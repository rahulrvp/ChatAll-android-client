package ch.bullfin.multilanguagechat.async;

import android.content.Context;
import android.os.AsyncTask;

import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.model.Message;

/**
 * Created by root on 12/10/14.
 */
public class SendMessageTask extends AsyncTask<Void, Void, Response> {
    private Context context;
    private Message message;
    private SendMessageCallback callback;

    public SendMessageTask(Context context, Message message, SendMessageCallback callback) {
        this.context = context;
        this.message = message;
        this.callback = callback;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            callback.onMessageSent();
        }
    }

    public interface SendMessageCallback {
        public abstract void onMessageSent();
    }
}
