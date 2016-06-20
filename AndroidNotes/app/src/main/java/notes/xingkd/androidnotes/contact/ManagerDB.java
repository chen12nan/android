package notes.xingkd.androidnotes.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lee on 16-6-19.
 */
public class ManagerDB {

    private int type = 1; // 0 :execSQL  1:query
    private final static String TABLENAME = "Contact";
    private  DBHelper mDBHelper;
    private ArrayList<Contact> mContacts = new ArrayList<Contact>();

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
            Log.v(ContactFragment.TAG, "onCreate()");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(ContactFragment.TAG, "DBHelper::onUpgrade()");
        }
    }

    public ManagerDB(Context context)
    {
        mDBHelper = new DBHelper(context);
        Log.v(ContactFragment.TAG, Integer.toString(getContacts().size()));
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

            long id = db.insert("Contact", null, values);
            if(id != -1)
            {
                contact.setId((short)id);
            }
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
            // new String[10] 创建数组，有10个元素
            // new String[]{10} 有 1 个元素
            db.update(TABLENAME, values, " id = ? ", new String[]{Short.toString(contact.getId())});
        }
        return true;
    }

    public Contact query(short id)
    {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        if(type == 0)
        {

        }
        else if(type == 1)
        {
            String[] columns = {"id", "name", "age", "phone"};
            String where= " id = ?";
            String[] args = {Short.toString(id)};
            Cursor cursor = db.query(TABLENAME, columns, where, args, null, null, null);
            Log.v(ContactFragment.TAG, Integer.toString(cursor.getCount()));
            Contact contact = new Contact();
            cursor.moveToFirst();
            for(int i = 0; i<cursor.getCount(); i++)
            {
                Log.v(ContactFragment.TAG, cursor.getString(cursor.getColumnIndex("name")));
                contact.setId(cursor.getShort(cursor.getColumnIndex("id")));
                contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                contact.setAge(cursor.getShort(cursor.getColumnIndex("age")));
                contact.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                return contact;
            }
        }
        return null;
    }

    public ArrayList<Contact> getContacts()
    {
        mContacts.clear();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        if(type == 0)
        {

        }
        else if(type == 1)
        {
            String[] columns = {"id", "name", "age", "phone"};
//            Cursor cursor = db.query(TABLENAME, columns, null, null, null, null, null);
            Cursor cursor = db.rawQuery("select * from Contact", null);
            Log.v(ContactFragment.TAG, Integer.toString(cursor.getCount()));
            cursor.moveToFirst();
            for(int i = 0; i<cursor.getCount(); i++)
            {
                Contact contact = new Contact();
                Log.v(ContactFragment.TAG, cursor.getString(cursor.getColumnIndex("name")));
                contact.setId(cursor.getShort(cursor.getColumnIndex("id")));
                contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                contact.setAge(cursor.getShort(cursor.getColumnIndex("age")));
                cursor.moveToNext();
                mContacts.add(contact);
            }
        }
        return mContacts;
    }
}
