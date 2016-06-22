package notes.xingkd.androidnotes.fragment;

import android.app.Fragment;

import notes.xingkd.androidnotes.contact.ContactFragment;

/**
 * Created by xkd on 16-6-22.
 */
public class FragmentFactory  {

    public enum FragmentType{
        ftContact,
        ftLoad
    }

    public static Fragment createFragment(FragmentType type)
    {
        if(type == FragmentType.ftContact)
        {
            return new ContactFragment();
        }
        else if(type == FragmentType.ftLoad)
        {
            return new LoadFragment();
        }
        else
        {
            return null;
        }
    }
}
