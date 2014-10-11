package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ch.bullfin.multilanguagechat.R;

public class MyChatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chats);
    }

    public void onFriendsClicked(View view) {
        Intent intent = new Intent(mContext, FriendListActivity.class);
        startActivity(intent);
    }
}
