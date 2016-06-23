package notes.xingkd.androidnotes.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

import notes.xingkd.androidnotes.MainActivity;
import notes.xingkd.androidnotes.R;

/**
 * Created by xkd on 16-6-23.
 */
public class TestPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    private Preference one;
    private Preference two;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.test_preference);

        one = findPreference("set_3g");
        one.setOnPreferenceChangeListener(this);
        one.setOnPreferenceClickListener(this);
    }



    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean ret = super.onPreferenceTreeClick(preferenceScreen, preference);
        Log.v(MainActivity.PREFER_TAG, "TestPreferenceFragment::onPreferenceTreeClick()");
        return ret;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.v(MainActivity.PREFER_TAG, "TestPreferenceFragment::onPreferenceChange()");
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        Log.v(MainActivity.PREFER_TAG, "TestPreferenceFragment::onPreferenceClick()");
        // return true, will not call onPreferenceTreeClick();
        return true;
    }
}
