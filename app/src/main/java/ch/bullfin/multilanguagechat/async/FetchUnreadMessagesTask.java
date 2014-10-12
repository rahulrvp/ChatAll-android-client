package ch.bullfin.multilanguagechat.async;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by root on 12/10/14.
 */
public class FetchUnreadMessagesTask extends AsyncTask<Void, Void, Response>{
    private Context context;
    private long chatId;
    private long timestamp;
    private FetchUnreadMessagesTaskCallback callback;

    public FetchUnreadMessagesTask(Context context, long chatId, long timestamp, FetchUnreadMessagesTaskCallback callback) {
        this.context = context;
        this.chatId = chatId;
        this.timestamp = timestamp;
        this.callback = callback;
    }

    @Override
    protected Response doInBackground(Void... voids) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("authentication_token", User.getInstance(context).getAuthenticationToken());
        params.put("last_message_received_at", String.valueOf(timestamp));

        return new HTTPManager("http://sandbox.bullfin.ch:9000/users/" +
                User.getInstance(context).getId() +
                "/chats/" + chatId + "/message_texts.json").get(params);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 200) {
            callback.onNewMessagesReceived(new Gson().fromJson(response.getResponseBody(), Message[].class));
        }
    }

    public interface FetchUnreadMessagesTaskCallback {
        public abstract void onNewMessagesReceived(Message[] messages);
    }
}
