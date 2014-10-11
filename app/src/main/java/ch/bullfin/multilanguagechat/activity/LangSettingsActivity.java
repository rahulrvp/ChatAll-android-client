package ch.bullfin.multilanguagechat.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.config.Configuration;

/**
 * Created by root on 11/10/14.
 */
public class LangSettingsActivity extends BaseActivity {

    private ArrayList<String> mDeviceLangList;
    private ArrayList<String> mSelectedLangList;
    private Button mSubmitLangButton;
    private Spinner mLangListSpinner;
    private HashMap<String,String> mLangMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_settings);

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
        //mSelectedLangList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.language_list)));
        //mSelectedLangList.retainAll(mDeviceLangList);

        ArrayAdapter<String> langSpinnerAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mDeviceLangList);
        langSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLangListSpinner.setAdapter(langSpinnerAdapter);

        Configuration currentConfig = Configuration.getInstance(mContext);
        if ( currentConfig != null) {
            Toast.makeText(mContext,currentConfig.getCurrentLang(mContext),Toast.LENGTH_LONG).show();
        }


        mSubmitLangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,mLangListSpinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                Configuration currentConfig = Configuration.getInstance(mContext);
                currentConfig.setCurrentLang(mLangMap.get(mLangListSpinner.getSelectedItem().toString()));
                currentConfig.save(mContext);
            }
        });

    }

}
