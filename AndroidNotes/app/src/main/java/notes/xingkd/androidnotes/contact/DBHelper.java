package notes.xingkd.androidnotes.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xkd on 16-6-20.
 */


public class DBHelper extends SQLiteOpenHelper {
    private String mDatabaseName = "";
    private String mTableName = "";
    private final static String TABLENAME="Contact";

    public DBHelper(Context context)
    {
        super(context, "Contacts.db", null, 1);
        Log.v(ContactFragment.TAG, "DEHelper::DBHelper()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLENAME + "(id integer primary key autoincrement, " +
                "name varchar(20)," +
                "age integer," +
                "phone varchar(40))");
        Log.v(ContactFragment.TAG, "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(ContactFragment.TAG, "DBHelper::onUpgrade()");
    }
}

