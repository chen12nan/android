package notes.xingkd.androidnotes.contact;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import notes.xingkd.androidnotes.R;

/**
 * Created by lee on 16-6-19.
 */
enum AdapterType{
    adArray,
    adSimple,
    adSimpleCursor,
    adBase
}
public class ContactFragment  extends Fragment{

    public final static  String TAG = "testContact";

    private ListView mListView = null;
    private AdapterType mAdType = AdapterType.adArray;
    private String[] items = {"Chinese", "English", "French", "Japanese", "American"};
    private ManagerDB mManagerDB = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, "onAttach");
        mManagerDB = new ManagerDB(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "ContactFragment::onCreateView");
        View view = inflater.inflate(R.layout.contactfragment, container, false);
        mListView = (ListView)view.findViewById(R.id.listView);
        initAdapter(AdapterType.adArray);

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
                    //Notes.intent();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    ContactFragment.this.getContext().startActivity(intent);
                }
            });
        }
        else if(AdapterType.adSimple == type)
        {

        }
        else if(AdapterType.adSimpleCursor == type)
        {

        }
        else if(AdapterType.adBase == type)
        {

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
