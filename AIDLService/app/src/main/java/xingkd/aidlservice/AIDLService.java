package xingkd.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xkd on 16-6-27.
 */
public class AIDLService extends Service {

    private Person mPerson;
    private IRemoteService.Stub mBinder = new IRemoteService.Stub(){
        @Override
        public int add(int a, int b) throws RemoteException {
            return (a+b);
        }

        @Override
        public Person getPerson(Person person) throws RemoteException {
            return person;
        }

        @Override
        public void setName(Person person, String name) throws RemoteException {
            Log.v("xingkd", "Servce::setName()");
            System.out.println("xingkd  service  " + Thread.currentThread().getId());
            person.setName(name);
            mPerson = person;
        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.v("xingkd", "AIDLService::onStart()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("xingkd", "AIDLService::onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("xingkd", "AIDLService::onBind()");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("xingkd  name = " + mPerson.getName());
        mPerson.setName("abc123");
        Log.v("xingkd", "AIDLService::onUnbind()");
        System.out.println(mPerson);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("xingkd", "AIDLService::onDestroy()");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.v("xingkd", "AIDLService::onRebind()");
    }
}
