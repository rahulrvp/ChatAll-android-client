package ch.bullfin.multilanguagechat.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by sreejith on 11/10/14.
 */
public class BaseActivity extends Activity {
    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
