package notes.xingkd.androidnotes.thread;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by xkd on 16-7-6.
 */
public class TestAsyncTask extends AsyncTask<String, String, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.v("AsyncTask", "doInBackground  " + params);
        return "xingkd";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.v("AsyncTask", "onProgressUpdate  " + values);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.v("AsyncTask", "onPostExecute  " + s);
    }
}
