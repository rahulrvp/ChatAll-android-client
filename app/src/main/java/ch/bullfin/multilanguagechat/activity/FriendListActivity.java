package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.FriendListAdapter;

public class FriendListActivity extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        context = this;

        FriendListAdapter friendListAdapter = new FriendListAdapter(mContext);
        ListView friendList = (ListView) findViewById(R.id.friends_list);

        if (friendList != null) {
            friendList.setAdapter(friendListAdapter);
            friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(context, ChatDetailsActivity.class));
                }
            });
        }
    }

    public void onChatsClicked(View view) {
        Intent intent = new Intent(mContext, MyChatsActivity.class);
        startActivity(intent);
    }
}
