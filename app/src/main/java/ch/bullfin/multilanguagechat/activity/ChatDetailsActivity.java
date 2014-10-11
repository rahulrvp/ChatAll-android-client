package ch.bullfin.multilanguagechat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatDetailsAdapter;

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
            ChatDetailsAdapter adapter = new ChatDetailsAdapter(this);
            listView.setAdapter(adapter);
        }
    }
}
