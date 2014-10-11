package ch.bullfin.multilanguagechat.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import ch.bullfin.httpmanager.HTTPManager;
import ch.bullfin.httpmanager.Response;
import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.activity.MyChatsActivity;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by sreejith on 19/5/14.
 */
public class LoginTask extends AsyncTask<Void,Void,Boolean> {
    User mCurrentUser;
    String mEmail;
    String mPassword;
    Context mContext;
    ProgressBar mLoginProgress;

    public LoginTask(String email, String password, Context context, ProgressBar loginProgress) {
        mContext = context;
        this.mCurrentUser = User.getInstance(mContext);
        mEmail = email;
        mPassword = password;
        mLoginProgress = loginProgress;
    }

    @Override
    protected void onPreExecute() {
        if(mLoginProgress != null) {
            mLoginProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Gson gson = new Gson();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", mEmail);
        params.put("password", mPassword);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("user", params);

        Response response = new HTTPManager("http://sandbox.bullfin.ch:9000/users/sign_in.json").post(gson.toJson(parameters));
        if (response.getStatusCode() == 200) {
            JSONObject jsonObject;
            JSONObject responceJson;
            try {
                responceJson = new JSONObject(response.getResponseBody());
                jsonObject = new JSONObject(responceJson.getString("user"));
                mCurrentUser.setAuthenticationToken(jsonObject.getString("authentication_token"));
                mCurrentUser.setName(jsonObject.getString("name"));
                mCurrentUser.setEmail(jsonObject.getString("email"));
                mCurrentUser.setId(jsonObject.getString("id"));
                mCurrentUser.save(mContext);
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(mLoginProgress != null) {
            mLoginProgress.setVisibility(View.INVISIBLE);
        }

        if (result) {
            Intent intent = new Intent(mContext,MyChatsActivity.class);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
