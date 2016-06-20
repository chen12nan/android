package notes.xingkd.androidnotes;

/**
 * Created by xkd on 16-6-20.
 */
public class Notes {

    public static void intent()
    {
        /**
         1、Intent() 空构造函数
         2、Intent(Intent o) 拷贝构造函数
         3、Intent(String action) 指定action类型的构造函数
         4、Intent(String action, Uri uri) 指定Action类型和Uri的构造函数，URI主要是结合程序之间的数据共享ContentProvider
         5、Intent(Context packageContext, Class<?> cls) 传入组件的构造函数，也就是上文提到的
         6、Intent(String action, Uri uri, Context packageContext, Class<?> cls) 前两种结合体
         */
    }

    public static  void multiThread()
    {
        /**
         http://www.cr173.com/html/19165_1.html

         据我所知android提供了以下几种方法，用于实现后台线程与UI线程的交互。
         1、handler
         2、Activity.runOnUIThread(Runnable)
         3、View.Post(Runnable)
         4、View.PostDelayed(Runnabe,long)
         5、AsyncTask
         */
    }
}
