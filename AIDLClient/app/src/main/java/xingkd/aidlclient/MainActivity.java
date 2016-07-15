package xingkd.aidlclient;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

import xingkd.aidlservice.IRemoteService;
import xingkd.aidlservice.Person;
import xingkd.aidlservice.R;

public class MainActivity extends Activity {

    private IRemoteService mRemote;
    private Person mPerson;
    private ServiceConnection sConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemote = IRemoteService.Stub.asInterface(service);
            try{
                int val = mRemote.add(13, 23);
                System.out.println("xingkd Client val = " + val);
                System.out.println("xingkd  client" + Thread.currentThread().getId());
                mPerson = new Person("zhengsh", 24);
                mRemote.setName(mPerson, "zsh");
            }
            catch (RemoteException e)
            {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;

        /**
         */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent();
        intent.setAction("xingkd.aidlservice");
        final Intent eintent = new Intent(createExplicitFromImplicitIntent(this,intent));
        bindService(eintent,sConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(sConnection);
    }
}
