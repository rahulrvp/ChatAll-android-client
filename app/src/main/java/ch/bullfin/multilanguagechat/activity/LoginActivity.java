package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.async.LoginTask;
import ch.bullfin.multilanguagechat.model.User;
import ch.bullfin.multilanguagechat.util.BFUtils;


public class LoginActivity extends BaseActivity {

    private ProgressBar mSignInProgress;
    private EditText mEmailField;
    private User mCurrentUser;
    private EditText mPasswordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCurrentUser = User.getInstance(mContext);
        mEmailField = (EditText) findViewById(R.id.login_username);
        mPasswordField = (EditText) findViewById(R.id.login_password);
        mSignInProgress = (ProgressBar) findViewById(R.id.login_progress);

        if (mCurrentUser.getAuthenticationToken() != null) {
            Intent intent = new Intent(mContext, MyChatsActivity.class);
            startActivity(intent);
        }
    }

    public void onLoginClicked(View view) {
        String email = BFUtils.getStringFromEditable(mEmailField);
        if(!BFUtils.isVaildEmailAddress(email)) {
            BFUtils.setError(mEmailField, getString(R.string.login_username_error));
            return;
        }

        String password = BFUtils.getStringFromEditable(mPasswordField);
        if(password == null || password.length() < 1) {
            BFUtils.setError(mPasswordField, getString(R.string.login_password_error));
            return;
        }
        new LoginTask(email, password, mContext, mSignInProgress).execute();
    }

    public void onSignupClicked(View view) {
        Intent intent = new Intent(mContext, SignUpActivity.class);
        startActivity(intent);
    }
}
