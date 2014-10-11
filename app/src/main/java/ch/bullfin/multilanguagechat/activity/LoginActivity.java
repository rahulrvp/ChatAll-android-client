package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ch.bullfin.multilanguagechat.R;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClicked(View view) {
        Intent intent = new Intent(mContext, FriendListActivity.class);
        startActivity(intent);
    }
}
