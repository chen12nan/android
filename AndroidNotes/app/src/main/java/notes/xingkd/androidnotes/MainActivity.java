package notes.xingkd.androidnotes;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;

/**
 * 需要学习：
 * 1、数据结构: list map hash
 * 2、Fragment: ListFragment DialogFragment
 * 3、SQL: SQLiteDatabase
 * 4、ListView and Adapter
 */
import notes.xingkd.androidnotes.contact.ContactFragment;

public class MainActivity extends Activity {

    private  Fragment content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = new ContactFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.main_content, content, "CONTACT");
        ft.addToBackStack("CONTACT");
        Log.v(ContactFragment.TAG, "MainActivity::onCreate()");
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(ContactFragment.TAG, "MainActivity::onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(ContactFragment.TAG, "MainActivity::onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(ContactFragment.TAG, "MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(ContactFragment.TAG, "MainActivity::onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // after onPause
        Log.v(ContactFragment.TAG, "MainActivity::onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(ContactFragment.TAG, "MainActivity::onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(ContactFragment.TAG, "MainActivity::onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(ContactFragment.TAG, "MainActivity::onDestroy()");
    }
}