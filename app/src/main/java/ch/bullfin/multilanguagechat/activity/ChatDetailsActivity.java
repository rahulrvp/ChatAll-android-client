package ch.bullfin.multilanguagechat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatDetailsAdapter;
import ch.bullfin.multilanguagechat.async.FetchUnreadMessagesTask;
import ch.bullfin.multilanguagechat.async.SendMessageTask;
import ch.bullfin.multilanguagechat.config.Config;
import ch.bullfin.multilanguagechat.model.Chat;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by root on 11/10/14.
 */
public class ChatDetailsActivity extends Activity {

    private ListView mListView;
    private ChatDetailsAdapter mAdapter;
    private EditText mMessageField;
    private long mChatId;
    private Chat mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        mChat = (Chat) getIntent().getSerializableExtra("chat");

        mMessageField = (EditText) findViewById(R.id.message_field);
        mListView = (ListView) findViewById(R.id.chat_details_list);
        if (mListView != null) {
            /* This is junk data */
            /*User user = new User();
            user.setId(1);
            user.setName("Rahul");

            Message message = new Message();
            message.setSender_language("en");
            message.setText("Hello dude, what's up.?");
            message.setSender(user);

            Message[] messages = new Message[2];
            messages[0] = message;

            Message message2 = new Message();
            message2.setSender_language("en");
            message2.setText("Fine bro");
            message2.setSender(user);

            User user2 = new User();
            user2.setId(0);
            user2.setName("Saneep");

            message2.setSender(user2);
            messages[1] = message2;*/

            mAdapter = new ChatDetailsAdapter(this);
            mAdapter.updateMessages(mChat.getMessage_texts());

            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onRefreshButton(View view) {
        new FetchUnreadMessagesTask(this,
                mChat.getId(),
                mAdapter.getLastTimestamp(),
                new FetchUnreadMessagesTask.FetchUnreadMessagesTaskCallback() {
                    @Override
                    public void onNewMessagesReceived(Message[] messages) {
                        mAdapter.updateMessages(messages);
                    }
                }).execute();
    }

    public void onConfigurationClicked(View view) {
        startActivity(new Intent(this, LangSettingsActivity.class));
    }

    public void onSendMessageClicked(View view) {
        String messageString = mMessageField.getText().toString();
        if (messageString != null && messageString.length() > 0) {
            final Message message = new Message();
            message.setCreated_at(System.currentTimeMillis() / 1000); // time in seconds
            message.setText(messageString);
            message.setSender_language(Config.getInstance(this).getLanguageCode());
            message.setSender(User.getInstance(this));
            message.setChat_id(mChat.getId());

            new SendMessageTask(this, message, new SendMessageTask.SendMessageCallback() {
                @Override
                public void onMessageSent() {
                    mMessageField.setText("");
                    Message[] messages = new Message[1];
                    messages[0]= message;
                    mAdapter.updateMessages(messages);
                }
            }).execute();
        }
    }


}
