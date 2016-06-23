package notes.xingkd.androidnotes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import notes.xingkd.androidnotes.contact.ContactFragment;
import notes.xingkd.androidnotes.datastruct.TestList;
import notes.xingkd.androidnotes.fragment.FragmentFactory;
import notes.xingkd.androidnotes.fragment.LoginDialogFragment;
import notes.xingkd.androidnotes.thread.TestThread;

/**
 * 需要学习：
 * 1、数据结构: list map hash
 * 2、Fragment: ListFragment DialogFragment
 * 3、SQL: SQLiteDatabase
 * 4、ListView and Adapter
 */

/**
 * LoginDialogFragment.LoginInputListener  测试LoginDialogFragment
  */
public class MainActivity extends Activity implements LoginDialogFragment.LoginInputListener {

    private  Fragment content;
    private  TestThread sThread;

    public final static String PREFER_TAG="PreferenceFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = FragmentFactory.createFragment(FragmentFactory.FragmentType.ftPreference);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.main_content, content, "CONTACT");
//        ft.addToBackStack("CONTACT");
        Log.v(ContactFragment.TAG, "MainActivity::onCreate()");
        ft.commit();
        System.out.println(Thread.currentThread().getId() +" main ");

        sThread = new TestThread();
        sThread.start();

        sThread.testRunnable();

        TestList.testList();
        Log.v(ContactFragment.TAG, "=================");
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

    // 创建搜索框， menu项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "ACCOUNT : " + username + " , PASSWD : " + password,
                Toast.LENGTH_LONG).show();
    }
}