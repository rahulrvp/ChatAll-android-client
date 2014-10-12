package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private TextView mFilterText;
    private TextView mCount;
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
        mFilterText = (TextView) findViewById(R.id.friend_filter_text);
        mCount = (TextView) findViewById(R.id.friend_list_count);

        new FetchFriendListTask().execute();

        if (mFriendList != null) {
            mFriendList.setAdapter(mFriendListAdapter);
            mFriendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(context, ChatDetailsActivity.class));
                }
            });
        }

        mFilterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void filter(String filterText) {
        ArrayList<User> filteredList = new ArrayList<User>();
        for (User user : mFriendLists) {
            if (checkField(user.getName(), filterText)) {
                filteredList.add(user);
            }
        }

        if (mFriendListAdapter != null) {
            mFriendListAdapter.setValues(filteredList);
        }

        if (mCount != null) {
            mCount.setText("" + filteredList.size());
        }
    }

    public boolean checkField(String value, String filterText) {
        if (value != null && filterText != null) {
            return (value.toLowerCase().contains(filterText.toLowerCase()));
        }
        return false;
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
            params.put("user_id", String.valueOf(User.getInstance(mContext).getId()));

            Response response = new HTTPManager("http://sandbox.bullfin.ch:9000/users.json").get(params);

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

            if (mFriendListAdapter != null) {
                mFriendListAdapter.setValues(result);
            }

            if (mCount != null) {
                mCount.setText("" + mFriendLists.size());
            }
        }
    }

    @Override
    public void onBackPressed() {
        new FetchFriendListTask().execute();
    }
}
