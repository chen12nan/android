package notes.xingkd.androidnotes.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.provider.ContactsContract.Contacts;
import android.app.LoaderManager;
import java.util.ArrayList;

import notes.xingkd.androidnotes.R;
import notes.xingkd.androidnotes.contact.ContactFragment;

/**
 * Created by xkd on 16-6-22.
 */
public class LoadFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView mListView = null;
    LoaderManager managerLoader = null;
    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
            Contacts._ID, Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS,
            Contacts.LOOKUP_KEY, };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(ContactFragment.TAG, "LoadFragment::onCreateView()");
        View view = inflater.inflate(R.layout.load_fragment, container, false);
        mListView = (ListView)view.findViewById(R.id.loadListView);
        System.out.println(mListView);
        managerLoader = getLoaderManager();
        managerLoader.initLoader(0, null, this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        System.out.println(Thread.currentThread().getId());
        CursorLoader loader = new CursorLoader(this.getContext(), Uri.parse("content://notes.xingkd/contact"),
                null, null, null, null);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        System.out.println("onLoaderFinish():   " + data.getCount());
        ArrayList<String> arrayList = new ArrayList<>();

        while(data.moveToNext())
        {
            String name = data.getString(data.getColumnIndex("name"));
            arrayList.add(name);
        }
        System.out.println(this.getContext());
        System.out.println(mListView);
        mListView.setAdapter(new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1, arrayList));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //用来释放对前面loader查询到的结果引用
    }
}
