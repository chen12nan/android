package notes.xingkd.androidnotes.http;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import notes.xingkd.androidnotes.R;


/**
 * Created by xkd on 16-8-25.
 */
public class JsonFragment extends Fragment {

    private TextView mTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.json_fragment, container, false);

        Button btn = (Button)view.findViewById(R.id.btnJson);
        mTextView = (TextView)view.findViewById(R.id.viewJson);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://192.168.12.66/test/json.php";
                mTextView.setText("Begin");

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 网络请求不能放在UI线程，否则会报android.os.NetworkOnMainThreadException异常
                        testURLConnection();
                    }
                });
                thread.start();
            }
        });
        return view;
    }

    public void testURLConnection()
    {
        try{
            URL url = new URL("http://172.16.23.80/test/json.php");
            try{
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                Log.v("abc ", "=======================================1=");
                httpURLConnection.setUseCaches(true);
                Log.v("abc ", "=====================================2===");
                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.connect();
                Log.v("abc ", "===================================3=====");
                int statusCode = httpURLConnection.getResponseCode();
                Log.v("abc ", "===================================4=====");
                if(statusCode == 200)
                {
                    BufferedInputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    byte []data = new byte[1024];
                    int len = inputStream.read(data);
                    String text = new String(data, 0, len);
                    Log.v("abc", "test json ============================" + text);
                    try{
                        JSONObject jsonObject = new JSONObject(text);
                        Log.v("abc", "title = " + jsonObject.get("title"));
                        Log.v("abc", "value = " + jsonObject.get("value"));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

    }
}