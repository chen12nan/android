package notes.xingkd.androidnotes.component;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import notes.xingkd.androidnotes.contact.ContactFragment;

/**
 * Created by xkd on 16-6-22.
 */
// testFragment

/**
 * http://blog.csdn.net/stonecao/article/details/6417364
 *
 * 在开发的过程中碰到一个棘手的问题，调用Activity.finish函数Acitivity没有执行生命周期的ondestory函数，
 * 后面查找半天是因为有一个handler成员，因为它有一个delay消息没有处理，
 * 调用Activity.finish，Activity不会马上destory，
 * 所以记得在Ativity finish前清理一下handle中的未处理的消息，
 * 这样Activity才会顺利的destory
 */

public class HandlerActivity extends Activity implements Runnable{

    public final static String handlerAction = "notes.xingkd.Handler";
    private Handler handler = null;
    private Thread mThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThread = new Thread(this);
        System.out.println("HandlerActivity::onCreate : idName = " + Thread.currentThread().getName());
        // handleMessage run in MainLooper(MainThread)
        handler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("HandlerActivity::handleMessage():  id = " + Thread.currentThread().getName());
                System.out.println("HandlerActivity::handleMessage():  msg.what = " + msg.what);
            }
        };
    }

    @Override
    public void run() {
        int count = 0;
        while(true)
        {
            Message msg = Message.obtain();
            msg.what = count;
            System.out.println("HandlerActivity::run():  id = " + Thread.currentThread().getName());
            handler.sendMessage(msg);
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                Log.v(ContactFragment.TAG, "run()");
            }
            count++;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mThread.start();
    }
}
