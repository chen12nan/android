package xingkd.aidlservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button btn = (Button)findViewById(R.id.btn1);
        btn.setOnClickListener(this);

    }

    private IRemoteService mRemote;
    private Person mPerson;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemote = IRemoteService.Stub.asInterface(service);
            try {
                int val = mRemote.add(10,20);
                mPerson = new Person("zhengsh", 24);
                mRemote.setName(mPerson, "zsh");
                System.out.println("xingkd:    val = " + val);
            }
            catch (RemoteException e)
            {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onClick(View v) {
        Log.v("xingkd", "onClicked");
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
