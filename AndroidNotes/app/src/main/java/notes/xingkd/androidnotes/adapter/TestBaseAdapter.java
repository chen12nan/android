package notes.xingkd.androidnotes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import notes.xingkd.androidnotes.R;
import notes.xingkd.androidnotes.contact.Contact;
import notes.xingkd.androidnotes.contact.ContactFragment;
import notes.xingkd.androidnotes.contact.ManagerDB;

/**
 * Created by xkd on 16-6-21.
 */
public class TestBaseAdapter extends BaseAdapter {

    private ManagerDB managerDB;
    private  Context mContext;

    public TestBaseAdapter(Context context){
        managerDB = new ManagerDB(context);
        mContext = context;
    }
    @Override
    public int getCount() {
        Log.v(ContactFragment.TAG, "TestBaseAdapter::getCount() ");
        return managerDB.getContacts().size();
    }

    @Override
    public long getItemId(int position) {
        Log.v(ContactFragment.TAG, "TestBaseAdapter::getItemId() ");
        return position;
    }

    @Override
    public Object getItem(int position) {
        Log.v(ContactFragment.TAG, "TestBaseAdapter::getItem() ");
        return managerDB.getContacts().get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v(ContactFragment.TAG, "TestBaseAdapter::getView() ");
        final Contact contact = (Contact)getItem(position);
        if(convertView == null)
        {
            Log.v(ContactFragment.TAG, "getView");
            convertView = LayoutInflater.from(mContext).inflate(R.layout.base_adapter, null);
            TextView textView =(TextView)convertView.findViewById(R.id.baseTxt);
            textView.setText(contact.getName());

            ImageView iv = (ImageView)convertView.findViewById(R.id.baseImg);
            iv.setImageResource(R.drawable.favorites);
        }
        return convertView;
    }
}
