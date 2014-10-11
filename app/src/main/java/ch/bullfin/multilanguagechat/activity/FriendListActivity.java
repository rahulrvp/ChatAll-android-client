package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.FriendListAdapter;

public class FriendListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        FriendListAdapter friendListAdapter = new FriendListAdapter(mContext);
        ListView friendList = (ListView) findViewById(R.id.friends_list);

        if (friendList != null) {
            friendList.setAdapter(friendListAdapter);
        }
    }

    public void onChatsClicked(View view) {
        Intent intent = new Intent(mContext, MyChatsActivity.class);
        startActivity(intent);
    }
}
