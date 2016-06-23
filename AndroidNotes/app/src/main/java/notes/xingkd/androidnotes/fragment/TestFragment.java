package notes.xingkd.androidnotes.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import notes.xingkd.androidnotes.R;
import notes.xingkd.androidnotes.component.HandlerActivity;
import notes.xingkd.androidnotes.preference.TestPreferenceActivity;

/**
 * Created by xkd on 16-6-20.
 */
public class TestFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        Button btn1 = (Button)view.findViewById(R.id.tf_btn1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)view.findViewById(R.id.tf_btn2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button)view.findViewById(R.id.tf_btn3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button)view.findViewById(R.id.tf_btn4);
        btn4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tf_btn1:
                TestDialogFragment testDialogFragment = new TestDialogFragment();
                testDialogFragment.show(getFragmentManager(), "TestDialog");
                break;
            case R.id.tf_btn2:
                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
                loginDialogFragment.show(getFragmentManager(), "LoginDialog");
                break;
            case R.id.tf_btn3:
//                Intent intent = new Intent(getActivity(), HandlerActivity.class);
                Intent intent = new Intent(HandlerActivity.handlerAction);
//                Intent intent = new Intent(Intent.ACTION_DIAL);
                getContext().startActivity(intent);
                break;
            case R.id.tf_btn4:
                Intent preferenceIntent = new Intent(getActivity(), TestPreferenceActivity.class);
                getContext().startActivity(preferenceIntent);
                break;
            default:
                break;
        }
    }
}
