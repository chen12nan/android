package notes.xingkd.androidnotes.contact;

import android.app.Fragment;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import notes.xingkd.androidnotes.Notes;
import notes.xingkd.androidnotes.R;
import notes.xingkd.androidnotes.adapter.TestBaseAdapter;

/**
 * Created by lee on 16-6-19.
 */
public class ContactFragment  extends Fragment{

    public final static  String TAG = "testContact";

    private ListView mListView = null;
    private AdapterType mAdType = AdapterType.adArray;
    private String[] items = {"Chinese", "English", "French", "Japanese", "American"};
    private ManagerDB mManagerDB = null;

    enum AdapterType{
        adArray,
        adSimple,
        adSimpleCursor,
        adBase
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "ContactFragment::onCreateView");
        View view = inflater.inflate(R.layout.contactfragment, container, false);
        mListView = (ListView)view.findViewById(R.id.contactListView);
        initAdapter(AdapterType.adBase);

        mManagerDB = new ManagerDB(this.getContext());
        return view;
    }

    private void initAdapter(AdapterType type)
    {
        if(mListView == null)
            return;

        if(AdapterType.adArray == type)
        {
            mListView.setAdapter(new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1, items));
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println(" position = " + position + "  id = " + id);
                    testProvider(position);
                }
            });
        }
        else if(AdapterType.adSimple == type)
        {
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("img", R.drawable.favorites);
            map1.put("name", "favorites");

            mapList.add(map1);

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("img", R.drawable.jmico2);
            map2.put("name", "encrypted");

            mapList.add(map2);


            mListView.setAdapter(new SimpleAdapter(getContext(), mapList, R.layout.simple_adapter,
                    new String[]{"img", "name"}, new int[]{R.id.img, R.id.txt}));
        }
        else if(AdapterType.adSimpleCursor == type)
        {

        }
        else if(AdapterType.adBase == type)
        {
            TestBaseAdapter adapter = new TestBaseAdapter(getContext());
            mListView.setAdapter(adapter);
        }
    }

    public void testIntent()
    {
        Notes.intent();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        this.getContext().startActivity(intent);
    }

    public void testDatabase()
    {
//        Contact contact = new Contact("xingkd", (short)position, "15120077642");
//        mManagerDB.insert(contact);
        Contact contact = mManagerDB.query((short)12);
        System.out.println(contact.getName() +" " +
                contact.getId() + " "  +
                contact.getAge() + " "  +
                contact.getPhone());

        contact.setName("abc");
        contact.setPhone("110");
        mManagerDB.update(contact);
    }

    public void testProvider(int position)
    {
        // 0:insert. 1:delete. 2:update. 3:query
        int type = position%4;
        Uri contactUris = Uri.parse("content://notes.xingkd/contact");
        Uri contactUri = Uri.parse("content://notes.xingkd/contact/27");

        if(type == 0)
        {
            ContentValues values = new ContentValues();
            values.put("name", "cprovier");
            values.put("age", type);
            values.put("phone", "hello");
            getContext().getContentResolver().insert(contactUris, values);
        }
        else if(type == 1)
        {
            getContext().getContentResolver().delete(contactUri, null, null);

        }
        else if(type == 2)
        {
            ContentValues values = new ContentValues();
            double d = Math.random();
            System.out.println("d = " + d);
            values.put("age", d);
            values.put("name", Double.toString(d));
            values.put("phone", "110");

            getContext().getContentResolver().update(contactUri, values, null, null);
        }
        else
        {
            String[] column = {"id", "name", "age", "phone"};
            String where = "age = 2";
            Cursor cursor = getContext().getContentResolver().query(contactUri, column, where, null, null);
            System.out.println("query--count =" + cursor.getCount());


        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "ContactFragment::onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "ContactFragment::onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "ContactFragment::onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "ContactFragment::onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "ContactFragment::onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, "ContactFragment::onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "ContactFragment::onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v(TAG, "ContactFragment::onDetach");

    }
}
