package ch.bullfin.multilanguagechat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatDetailsAdapter;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by root on 11/10/14.
 */
public class ChatDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        ListView listView = (ListView) findViewById(R.id.chat_details_list);
        if (listView != null) {
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

            ChatDetailsAdapter adapter = new ChatDetailsAdapter(this);
            adapter.updateMessages(messages);

            listView.setAdapter(adapter);
        }
    }
}
