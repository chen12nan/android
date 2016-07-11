package notes.xingkd.androidnotes.component;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import notes.xingkd.androidnotes.R;
import notes.xingkd.androidnotes.contact.ContactFragment;
/**
 * Created by xkd on 16-7-11.
 */
public class TitleBarActivity extends Activity {

    private  final static String MENU_TAG="TitleBar_Menu";

    private Button leftButton;
    private Button rightButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //声明使用自定义标题
        //设置窗体样式
        getWindow().setFeatureInt(
                Window.FEATURE_CUSTOM_TITLE,  //设置此样式为自定义样式
                R.layout.titlebar_titlebar //设置对应的布局
        );//自定义布局赋值
        leftButton=(Button)findViewById(R.id.titleBarButton);
//        leftButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                showDialog(R.id.btnLeft);
//            }
//        });
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        AlertDialog.Builder builder= new AlertDialog.Builder(TitleBarActivity.this);
        //设置标题
        if(id==R.id.btnLeft){
            builder.setTitle("故障返回主界面");
        }else if(id==R.id.btnRight){
            builder.setTitle("故障信息发布");
        }

        //确定按钮的操作
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //销毁对话框
                dialog.dismiss();
                TitleBarActivity.this.finish();
            }
        });
        //取消按钮的操作
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    /*
   创建搜索框， menu项
   http://android.blog.51cto.com/268543/306424/
   Android系统里面有3种类型的菜单：options menu，context menu，sub menu。
   在Activity里面，一般通过以下函数来使用options menu：
   Activity::onCreateOptionsMenu (Menu menu)   创建options menu，这个函数只会在menu第一次显示时调用。
   Activity::onPrepareOptionsMenu (Menu menu)  更新改变options menu的内容，这个函数会在menu每次显示时调用。
   Activity::onOptionsItemSelected (MenuItem item) 处理选中的菜单项。

   context menu    要在相应的view上按几秒后才显示的，用于view，跟某个具体的view绑定在一起。
   这类型的菜单不支持icon和快捷键！
   在Activity里面，一般通过以下函数来使用context menu：
   Activity::registerForContextMenu(View view) 为某个view注册context menu，一般在Activity::onCreate里面调用。
   Activity::onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) 创建context menu，
   和options menu不同，context meun每次显示时都会调用这个函数。
   Activity::onContextItemSelected(MenuItem item) 处理选中的菜单项
   */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        // optionsmenu 不支持icon ，只支持string
        menuInflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
        Log.v(MENU_TAG, "MainActivity::registerForContextMenu()   ");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.v(MENU_TAG, "MainActivity::onCreateContextMenu() ");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.v(MENU_TAG, "MainActivity::onContextItemSelected  ");
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Log.v(ContactFragment.TAG, "MainActivity::onMenuItemSelected() ======  " + featureId + "  " + item.getItemId());
        switch (item.getItemId())
        {
            case R.id.menu_first:
                Log.v(MENU_TAG, "    title ==== " + item.getTitle());
                break;
            case R.id.menu_second:
                Log.v(MENU_TAG, "    title ==== " + item.getTitle());
                break;
            case R.id.menu_third:
                Log.v(MENU_TAG, "    title ==== " + item.getTitle());
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

}
