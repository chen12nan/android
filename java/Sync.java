import com.test.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//import javax.swing.JButton;

public class Sync{

	private static  class ThreadA extends Thread
	{
		private Object  mObj;
		public ThreadA(Object obj)
		{
			mObj = obj;
		}

		public void run()
		{
			if(null != mObj)
			{
				synchronized(mObj)
				{
					System.out.println("ThreadA begin ... ");
					try{
						Thread.sleep(3000);
						System.out.println("ThreadA finish ------");
						mObj.notify();
					}
					catch(InterruptedException e)
					{
						System.out.println("  catch---------------- ");
						e.printStackTrace();
					}
					System.out.println("ThreadA end !!!!");
				}
			}
		}
	}

	private static  class MyThread extends Thread
	{
		private Object  mObj;
		public MyThread(Object obj)
		{
			mObj = obj;
		}

		public void run()
		{
			if(null != mObj)
			{
				synchronized(mObj)
				{
					System.out.println(" MyThread begin ... ");
					try{
						Thread.sleep(3000);
						System.out.println("waitint for ThreadA finish  ----");
						mObj.wait();
					}
					catch(InterruptedException e)
					{
						System.out.println("  catch---------------- ");
						e.printStackTrace();
					}
					System.out.println("MyThread end !!!!");
				}
			}
		}
	}

	public static void main(String[] args)
	{
		testTransient();
		System.out.println("======================");
	}
	
	public static void testTransient()
	{
		DataStruct.UserInfo userInfo = new DataStruct.UserInfo("Jim", "123456");
		DataStruct.UserInfo uInfo = new DataStruct.UserInfo("Tom", "123456");
		System.out.println(userInfo);

		try{
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserInfo.out"));
			o.writeObject(userInfo);
			o.writeObject(uInfo);
			o.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.out"));
			DataStruct.UserInfo info = (DataStruct.UserInfo) in.readObject();
			DataStruct.UserInfo ui = (DataStruct.UserInfo) in.readObject();
			System.out.println(info.toString());
			System.out.println(ui.toString());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void testNotify_Wait()
	{
		Object obj = new Object();

		new MyThread(obj).start();
		new ThreadA(obj).start();

		String str = "xingkd";
		str = "hello";
		/*Test test = new Test();
		test.show();*/

		synchronized(obj){
			System.out.println(Object.class.getName());
		}
		System.out.println("Hello World\n");		

		/*JButton jb = new JButton();
		jb.show();*/
	}
}

