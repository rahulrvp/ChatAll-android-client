package ch.bullfin.multilanguagechat.async;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.config.Config;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.User;

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
        long userId = User.getInstance(context).getId();
        long chatId = message.getChat_id();

        HashMap<String, String> messageText = new HashMap<String, String>();
        messageText.put("sender_language", Config.getInstance(context).getLanguageCode());
        messageText.put("text", message.getText());
        messageText.put("sender_id", String.valueOf(User.getInstance(context).getId()));

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("authentication_token", User.getInstance(context).getAuthenticationToken());
        params.put("message_text", messageText);

        return new HTTPManager("http://sandbox.bullfin.ch:9000/users/" + userId + "/chats/" + chatId + "/message_texts.json").post(new Gson().toJson(params));
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response.getStatusCode() == 201) {
            callback.onMessageSent();
        }
    }

    public interface SendMessageCallback {
        public abstract void onMessageSent();
    }
}
