package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.ChatListAdapter;
import ch.bullfin.multilanguagechat.async.FetchchatsTask;
import ch.bullfin.multilanguagechat.model.Chat;

public class MyChatsActivity extends BaseActivity {

    private Handler mHandler;
    private Context context;
    private ChatListAdapter mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chats);

        context = this;

        mHandler = new Handler();

        mChatListAdapter = new ChatListAdapter(mContext);
        ListView chatList = (ListView) findViewById(R.id.friends_list);

        new FetchchatsTask(mContext, new FetchchatsTask.FetchChatsCallback() {
            @Override
            public void onFetchchatsCompleted(Chat[] chat) {
                mChatListAdapter.setValues(chat);
            }

            @Override
            public void onFetchchatsFailed() {

            }
        }).execute();

        if (chatList != null) {
            chatList.setAdapter(mChatListAdapter);
            chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, ChatDetailsActivity.class);
                    intent.putExtra("chat", mChatListAdapter.getItem(i));
                    startActivity(intent);
                }
            });
        }
    }

    public void onFriendsClicked(View view) {
        Intent intent = new Intent(mContext, FriendListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mHandler != null) {
            mHandler.post(fetchMyChatsRunnable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHandler != null) {
            mHandler.removeCallbacks(fetchMyChatsRunnable);
        }
    }

    private Runnable fetchMyChatsRunnable = new Runnable() {
        @Override
        public void run() {
            new FetchchatsTask(context, new FetchchatsTask.FetchChatsCallback() {
                @Override
                public void onFetchchatsCompleted(Chat[] chat) {
                    mChatListAdapter.setValues(chat);
                }

                @Override
                public void onFetchchatsFailed() {

                }
            }).execute();

            mHandler.postDelayed(fetchMyChatsRunnable, 1000);
        }
    };

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
