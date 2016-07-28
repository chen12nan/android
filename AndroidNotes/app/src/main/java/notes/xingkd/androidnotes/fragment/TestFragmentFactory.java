package notes.xingkd.androidnotes.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;

import notes.xingkd.androidnotes.component.HandlerActivity;
import notes.xingkd.androidnotes.component.TitleBarActivity;
import notes.xingkd.androidnotes.preference.TestPreferenceActivity;

/**
 * Created by xkd on 16-7-27.
 */
public class TestFragmentFactory {

    private ArrayMap<String, ItemAction> maps = new ArrayMap<>();
    private Context m_context;
    private static TestFragmentFactory m_instance = null;
    private TestFragmentFactory(Context context)
    {
        m_context = context;
        maps.put("TestDialog", new TestDialogAction());
        maps.put("LoginDialog", new LoginDialogAction());
        maps.put("HandlerActivity", new HandlerActivityAction());
        maps.put("TestPreferenceActivity", new TestPreferenceActivityAction());
        maps.put("TitleBarActivity", new TitleBarActivityAction());
    }

    public static TestFragmentFactory factoryInstance(Context context)
    {
        if(m_instance == null)
        {
            m_instance = new TestFragmentFactory(context);
        }
        return m_instance;
    }

    public void operator(String name)
    {
        if(maps.containsKey(name))
        {
            maps.get(name).onAction();
        }
    }

    public abstract class ItemAction{
        abstract public void onAction();
    }

    private  class TestDialogAction extends ItemAction{
        public void onAction(){
            TestDialogFragment testDialogFragment = new TestDialogFragment();
            testDialogFragment.show(((Activity)m_context).getFragmentManager(), "TestDialog");
        }
    }

    private class LoginDialogAction extends  ItemAction{
        @Override
        public void onAction() {
            LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
            loginDialogFragment.show(((Activity)m_context).getFragmentManager(), "LoginDialog");
        }
    }

    private class HandlerActivityAction extends ItemAction{
        @Override
        public void onAction() {
//          Intent intent = new Intent(getActivity(), HandlerActivity.class);
            Intent intent = new Intent(HandlerActivity.handlerAction);
//          Intent intent = new Intent(Intent.ACTION_DIAL);
            m_context.startActivity(intent);
        }
    }

    private class TestPreferenceActivityAction extends ItemAction{
        @Override
        public void onAction() {
            Intent preferenceIntent = new Intent(m_context, TestPreferenceActivity.class);
            m_context.startActivity(preferenceIntent);
        }
    }

    private class TitleBarActivityAction extends ItemAction {
        @Override
        public void onAction() {
            Intent titleBarIntent = new Intent(m_context, TitleBarActivity.class);
            m_context.startActivity(titleBarIntent);

        }
    }
}
