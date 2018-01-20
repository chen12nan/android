package notes.xingkd.androidnotes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import notes.xingkd.androidnotes.R;

/**
 * Created by xkd on 16-6-20.
 */
public class TestFragment extends Fragment implements View.OnClickListener{

    private String[] items = {"TestDialog", "LoginDialog",
            "HandlerActivity", "TitleBarActivity", "TestPreferenceActivity", "AsyncTaskFragment"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        ListView listView = (ListView)view.findViewById(R.id.tf_listView);
        listView.setAdapter(new ArrayAdapter<String>(this.getActivity(),
                        android.R.layout.simple_list_item_1, items));

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestFragmentFactory.factoryInstance(getContext()).operator(items[position]);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.tf_btn1:
//                TestDialogFragment testDialogFragment = new TestDialogFragment();
//                testDialogFragment.show(getFragmentManager(), "TestDialog");
//                break;
//            case R.id.tf_btn2:
//                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
//                loginDialogFragment.show(getFragmentManager(), "LoginDialog");
//                break;
//            case R.id.tf_btn3:
////                Intent intent = new Intent(getActivity(), HandlerActivity.class);
//                Intent intent = new Intent(HandlerActivity.handlerAction);
////                Intent intent = new Intent(Intent.ACTION_DIAL);
//                getContext().startActivity(intent);
//                break;
//            case R.id.tf_btn4:
//                Intent preferenceIntent = new Intent(getActivity(), TestPreferenceActivity.class);
//                getContext().startActivity(preferenceIntent);
//                break;
//            case R.id.tf_btn5:
//                Intent titleBarIntent = new Intent(getActivity(), TitleBarActivity.class);
//                getContext().startActivity(titleBarIntent);
//            default:
//                break;
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_menu,menu);
    }
}
