package ch.bullfin.multilanguagechat.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.model.User;
import ch.bullfin.multilanguagechat.util.BFUtils;

public class SignUpActivity extends BaseActivity {

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPhoneNumberField;
    private String mPasswordText;
    private ProgressBar mSignUpSpinner;
    private User mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstNameField = (EditText) findViewById(R.id.signup_first_name);
        mLastNameField = (EditText) findViewById(R.id.signup_last_name);
        mEmailField = (EditText) findViewById(R.id.signup_email);
        mPasswordField = (EditText) findViewById(R.id.signup_password);
        mPhoneNumberField= (EditText) findViewById(R.id.signup_phone);
        mSignUpSpinner = (ProgressBar) findViewById(R.id.signup_progress);
        mCurrentUser = User.getInstance(mContext);
    }


    public void onSignUpClicked(View view) {

        mCurrentUser.setEmail(BFUtils.getStringFromEditable(mEmailField));
        if (mEmailField != null && !BFUtils.isVaildEmailAddress(mCurrentUser.getEmail())){
            BFUtils.setError(mEmailField, getString(R.string.signup_email_error));
            return;
        }

        mCurrentUser.setName(BFUtils.getStringFromEditable(mFirstNameField));
        if (mCurrentUser.getName() == null && mFirstNameField != null ) {
            BFUtils.setError(mFirstNameField, getString(R.string.signup_first_name_error));
            return;
        }

        mPasswordText = BFUtils.getStringFromEditable(mPasswordField);
        if (mPasswordText == null && mPasswordField != null ) {
            BFUtils.setError(mPasswordField, getString(R.string.signup_password_error));
            return;
        }

        mCurrentUser.setPhoneNumber(BFUtils.getStringFromEditable(mPhoneNumberField));
        if (mCurrentUser.getPhoneNumber() == null && mPhoneNumberField != null ) {
            BFUtils.setError(mPhoneNumberField, getString(R.string.signup_phone_number_error));
            return;
        }

        new SignUpTask().execute();

    }

    private class SignUpTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            if (mSignUpSpinner != null) {
                mSignUpSpinner.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            Gson gson = new Gson();

            HashMap<String,Object> params = new HashMap<String, Object>();

            params.put("email",mCurrentUser.getEmail());
            params.put("password",mPasswordText);
            params.put("password_confirmation",mPasswordText);

            HashMap<String,Object> parameter = new HashMap<String,Object>();
            HashMap<String,Object> profileParams = new HashMap<String,Object>();
            profileParams.put("phone_number",mCurrentUser.getPhoneNumber());
            profileParams.put("first_name",mCurrentUser.getName());
            profileParams.put("last_name",mCurrentUser.getName());
            params.put("profile_attributes",profileParams);
            parameter.put("user", params);

            Response response = new HTTPManager("http://sandbox.bullfin.ch:9000/users.json").post(gson.toJson(parameter));

            return response.getStatusCode();
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (mSignUpSpinner != null) {
                mSignUpSpinner.setVisibility(View.INVISIBLE);
            }

            if (result == 200) {
                mCurrentUser.save(mContext);
                Toast.makeText(mContext, getResources().getString(R.string.signup_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, getResources().getString(R.string.signup_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
