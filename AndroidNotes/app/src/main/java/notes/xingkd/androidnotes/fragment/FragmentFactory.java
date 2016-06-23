package notes.xingkd.androidnotes.fragment;

import android.app.Fragment;

import notes.xingkd.androidnotes.contact.ContactFragment;

/**
 * Created by xkd on 16-6-22.
 */
public class FragmentFactory  {

    public enum FragmentType{
        ftDefault,
        ftContact,
        ftLoad,
        ftList,
        ftDialog,
        ftLoginDialog,
        ftPreference
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
        else if(type == FragmentType.ftList)
        {
            return new TestListFragment();
        }
        else if(type == FragmentType.ftDialog)
        {
            return new TestDialogFragment();
        }
        else if(type == FragmentType.ftDefault)
        {
            return new TestFragment();
        }
        else if(type == FragmentType.ftLoginDialog)
        {
            return new LoginDialogFragment();
        }
        else if(type == FragmentType.ftPreference)
        {
            return new TestPreferenceFragment();
        }
        else
        {
            return null;
        }
    }
}
