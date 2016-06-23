package notes.xingkd.androidnotes.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by xkd on 16-6-22.
 */
public class TestListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> items = new ArrayList<>();
        items.add("one");
        items.add("two");
        items.add("three");

        setListAdapter(new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, items));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
