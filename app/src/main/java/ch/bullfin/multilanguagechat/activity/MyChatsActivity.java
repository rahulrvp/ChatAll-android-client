package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatListAdapter;
import ch.bullfin.multilanguagechat.async.FetchchatsTask;
import ch.bullfin.multilanguagechat.model.Chat;

public class MyChatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chats);

        final ChatListAdapter chatListAdapter = new ChatListAdapter(mContext);
        ListView chatList = (ListView) findViewById(R.id.friends_list);

        new FetchchatsTask(mContext, new FetchchatsTask.FetchChatsCallback() {
            @Override
            public void onFetchchatsCompleted(Chat[] chat) {
                chatListAdapter.setValues(chat);
            }

            @Override
            public void onFetchchatsFailed() {

            }
        }).execute();

        if (chatList != null) {
            chatList.setAdapter(chatListAdapter);
            chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, ChatDetailsActivity.class);
                    intent.putExtra("chat", chatListAdapter.getItem(i));
                    startActivity(intent);
                }
            });
        }
    }

    public void onFriendsClicked(View view) {
        Intent intent = new Intent(mContext, FriendListActivity.class);
        startActivity(intent);
    }
}
