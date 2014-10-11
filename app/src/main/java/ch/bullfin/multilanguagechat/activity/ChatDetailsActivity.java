package ch.bullfin.multilanguagechat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatDetailsAdapter;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by root on 11/10/14.
 */
public class ChatDetailsActivity extends Activity {

    private ListView mListView;
    private ChatDetailsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        mListView = (ListView) findViewById(R.id.chat_details_list);
        if (mListView != null) {
            /* This is junk data */
            User user = new User();
            user.setId(1);
            user.setName("Rahul");

            Message message = new Message();
            message.setLanguage_code("en");
            message.setText("Hello dude, what's up.?");
            message.setSender(user);

            Message[] messages = new Message[2];
            messages[0] = message;

            Message message2 = new Message();
            message2.setLanguage_code("en");
            message2.setText("Fine bro");
            message2.setSender(user);

            User user2 = new User();
            user2.setId(0);
            user2.setName("Saneep");

            message2.setSender(user2);
            messages[1] = message2;

            mAdapter = new ChatDetailsAdapter(this);
            mAdapter.updateMessages(messages);

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

    public void onConfigurationClicked(View view) {
        startActivity(new Intent(this, LangSettingsActivity.class));
    }
}
