package ch.bullfin.multilanguagechat.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import ch.bullfin.multilanguagechat.R;


public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new LoaderTask().execute();
    }

    private class LoaderTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            // show loading indicator here
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }
    }
}
