package notes.xingkd.androidnotes.thread;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by xkd on 16-6-22.
 */
public class HandleThread extends Thread {

    private Handler mHandler;
    @Override
    public void run() {
        super.run();
        Looper.prepare();

    }
}
