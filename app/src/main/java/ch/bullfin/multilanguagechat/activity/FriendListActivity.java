package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.adapter.FriendListAdapter;
import ch.bullfin.multilanguagechat.model.User;

public class FriendListActivity extends BaseActivity {
    private Context context;

    private ProgressBar mPropertySpinner;
    private ArrayList<User> mFriendLists;
    private FriendListAdapter mFriendListAdapter;
    private ListView mFriendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        context = this;

        mFriendListAdapter = new FriendListAdapter(mContext);
        mFriendList = (ListView) findViewById(R.id.friends_list);
        mPropertySpinner = (ProgressBar) findViewById(R.id.friends_list_progress);
        mFriendLists = new ArrayList<User>();

        if (mFriendList != null) {
            mFriendList.setAdapter(mFriendListAdapter);
            mFriendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private class FetchFriendListTask extends AsyncTask<Void, Void, ArrayList<User>> {

        @Override
        protected void onPreExecute() {
            if (mPropertySpinner != null) {
                mPropertySpinner.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected ArrayList<User> doInBackground(Void... voids) {
            Gson gson = new Gson();
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("user_id", "1");

            Response response = new HTTPManager("URL").get(params);

            if (response.getStatusCode() == 200) {
                User[] users = gson.fromJson(response.getResponseBody(), User[].class);
                mFriendLists = new ArrayList<User>();
                Collections.addAll(mFriendLists, users);


            }

            return mFriendLists;
        }

        @Override
        protected void onPostExecute(ArrayList<User> result) {
            if (mPropertySpinner != null) {
                mPropertySpinner.setVisibility(View.GONE);
            }
        }
    }
}
