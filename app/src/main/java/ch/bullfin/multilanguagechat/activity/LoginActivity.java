package ch.bullfin.multilanguagechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ch.bullfin.multilanguagechat.R;
import utils.BFUtils;


public class LoginActivity extends ActionBarActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;
    private ProgressBar mSignInProgress;
    private Context mContext;

    private String SIGNIN_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.login_username);
        mPassword = (EditText) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mSignInProgress = (ProgressBar) findViewById(R.id.login_progress);

    }

    public void onSignInClicked(View view) {

        String email = BFUtils.getStringFromEditable(mUsername);
        if(!BFUtils.isValidEmailAddress(email)) {
            BFUtils.setError(mUsername, getString(R.string.login_username_error));
            return;
        }

        String password = BFUtils.getStringFromEditable(mPassword);
        if(password == null || password.length() < 1) {
            BFUtils.setError(mPassword, getString(R.string.login_password_error));
            return;
        }

        new SignInTask(email, password).execute();
}

    private class SignInTask extends AsyncTask<Void,Void,Boolean> {
        String mEmail;
        String mPassword;

        private SignInTask(String email, String password) {
            this.mEmail = email;
            this.mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            if(mSignInProgress != null && mLoginButton != null) {
                mSignInProgress.setVisibility(View.VISIBLE);
                mLoginButton.setEnabled(false);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Gson gson = new Gson();
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", mEmail);
            params.put("password", mPassword);

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("agency_admin", params);

            Response response = new HTTPManager(SIGNIN_URL).post(gson.toJson(parameters));
            if (response.getStatusCode() == 200) {
                JSONObject jsonObject;
                JSONObject responceJson;
                try {
                    responceJson = new JSONObject(response.getResponseBody());
                    jsonObject = new JSONObject(responceJson.getString("resource"));
                    return  true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(mSignInProgress != null && mLoginButton != null) {
                mSignInProgress.setVisibility(View.INVISIBLE);
                mLoginButton.setEnabled(true);
            }

            if (result) {
                Toast.makeText(mContext, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, PropertyListAcivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(mContext, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}


