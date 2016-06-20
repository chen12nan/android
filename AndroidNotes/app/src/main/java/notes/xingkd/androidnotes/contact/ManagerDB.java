package notes.xingkd.androidnotes.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lee on 16-6-19.
 */
public class ManagerDB {

    private int type = 0; // 0 :execSQL  1:query
    private final static String TABLENAME = "Contact";

    private class DBHelper extends SQLiteOpenHelper{

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
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(ContactFragment.TAG, "DBHelper::onUpgrade()");
        }
    }

    private  DBHelper mDBHelper;
    private ArrayList<Contact> mContacts;

    public ManagerDB(Context context)
    {
        mDBHelper = new DBHelper(context);
    }

    public boolean insert(Contact contact)
    {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if(type == 0)
        {
            db.execSQL("insert into " + TABLENAME + "(name, age, phone) values(?, ?, ?)",
                    new Object[]{contact.getName(), contact.getAge(), contact.getPhone()});
        }
        else if(type == 1)
        {
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("age", contact.getAge());
            values.put("phone", contact.getPhone());

            db.insert("Contact", null, values);
        }
        return true;
    }

    public boolean delete(short id)
    {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if(type == 0)
        {
            db.execSQL("delete from " + TABLENAME + " where id = ? ",
                    new Object[id]);
        }
        else if(type == 1)
        {
            db.delete("Contact", "where id = ?", new String[]{Short.toString(id)});
        }
        return true;
    }

    public boolean update(Contact contact)
    {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if(type == 0)
        {
            db.execSQL("update " + TABLENAME +" set name=?, age=?, phone=? where id = ?",
                    new Object[]{contact.getName(), contact.getAge(), contact.getPhone(), contact.getId()});
        }
        else if(type == 1)
        {
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("age", contact.getAge());
            values.put("phone", contact.getPhone());
            values.put("id", contact.getId());

            db.update(TABLENAME, values, "where id = ?", new String[contact.getId()]);
        }
        return true;
    }

    public Contact query(short id)
    {
        return null;
    }

    public ArrayList<Contact> getContacts()
    {
        return mContacts;
    }
}
