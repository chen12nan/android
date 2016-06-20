package notes.xingkd.androidnotes.thread;

import android.util.Log;

import notes.xingkd.androidnotes.contact.ContactFragment;

/**
 * Created by xkd on 16-6-20.
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        super.run();
        while (true)
        {
            System.out.println(currentThread().getId() + "jhkhkh:");
            try{
                sleep(10000);
            }
            catch (InterruptedException e)
            {
                Log.v(ContactFragment.TAG, "run()");
            }
        }
    }

    public static void testRunnable()
    {
        TestRunnable runnable = new TestRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class TestRunnable implements Runnable{

    @Override
    public void run() {

        System.out.println("TestRunable::run()");
        System.out.println(Thread.currentThread().getId() + "  runnable ");
        System.out.println("TestRunable::run()");

    }
}