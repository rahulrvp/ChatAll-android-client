package ch.bullfin.multilanguagechat.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.config.Config;

/**
 * Created by root on 11/10/14.
 */
public class LangSettingsActivity extends BaseActivity {

    private ArrayList<String> mDeviceLangList;
    private ArrayList<String> mSelectedLangList;
    private Button mSubmitLangButton;
    private Spinner mLangListSpinner;
    private HashMap<String,String> mLangMap;
    private Config mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_settings);

        mConfig = Config.getInstance(this);

        mSubmitLangButton = (Button) findViewById(R.id.submit_lang_button);
        mLangListSpinner = (Spinner) findViewById(R.id.lang_list_spinner);

        mDeviceLangList = new ArrayList<String>();
        mSelectedLangList = new ArrayList<String>();
        mLangMap = new HashMap<String, String>();

        Locale[] locs= Locale.getAvailableLocales();
        for(Locale l:locs){
            mDeviceLangList.add(l.getDisplayLanguage());
            mLangMap.put(l.getDisplayLanguage(),l.getLanguage());
            Log.d("lang", l.getDisplayLanguage()+" "+l.getLanguage());
        }

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(mDeviceLangList);
        mDeviceLangList.clear();
        mDeviceLangList.addAll(hashSet);
        Collections.sort(mDeviceLangList);

        //mSelectedLangList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.language_list)));
        //mSelectedLangList.retainAll(mDeviceLangList);

        ArrayAdapter<String> langSpinnerAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mDeviceLangList);
        langSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLangListSpinner.setAdapter(langSpinnerAdapter);

        mLangListSpinner.setSelection(mDeviceLangList.indexOf(mConfig.getLanguageName()));
    }

    public void onUpdateClicked(View view) {
        mConfig.setLanguageName(mLangListSpinner.getSelectedItem().toString());
        mConfig.setLanguageCode(mLangMap.get(mLangListSpinner.getSelectedItem().toString()));
        mConfig.save(this);
        finish();
    }
}
