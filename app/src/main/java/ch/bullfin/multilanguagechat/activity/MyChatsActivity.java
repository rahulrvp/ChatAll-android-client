package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatListAdapter;

public class MyChatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chats);

        ChatListAdapter chatListAdapter = new ChatListAdapter(mContext);
        ListView chatList = (ListView) findViewById(R.id.friends_list);

        if (chatList != null) {
            chatList.setAdapter(chatListAdapter);
            chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(mContext, ChatDetailsActivity.class));
                }
            });
        }
    }

    public void onFriendsClicked(View view) {
        Intent intent = new Intent(mContext, FriendListActivity.class);
        startActivity(intent);
    }
}
