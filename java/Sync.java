import com.test.*;
//import javax.swing.JButton;

public class Sync{

	private class Mute
	{
	}

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

