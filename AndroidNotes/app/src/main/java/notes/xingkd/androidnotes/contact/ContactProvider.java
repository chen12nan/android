package notes.xingkd.androidnotes.contact;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xkd on 16-6-20.
 */
public class ContactProvider extends ContentProvider {

    private final static String authority="notes.xingkd";
    private final static String CONTACTS_PATH="contact";
    private final static String CONTACT_PATH="contact/#";
    private DBHelper mDBHelper;

    // 用UriMatcher 过滤数据库中的表
    //
    private final static UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private final static int CONTACT_ALL=1;
    private final static int CONTACT_ONE=2;
    static {
        sMatcher.addURI(authority, CONTACTS_PATH, CONTACT_ALL);
        sMatcher.addURI(authority, CONTACT_PATH, CONTACT_ONE);
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        Log.v(ContactFragment.TAG, "ContactProvider::onCreate()");
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.v(ContactFragment.TAG, "ContactProvider::getType()");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(ContactFragment.TAG, "ContactProvider::insert()");
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long id = db.insert("Contact", "name", values);
        System.out.println(" ContactProvider::insert ---- id = " + id);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(ContactFragment.TAG, "ContactProvider::delete()");
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Log.v(ContactFragment.TAG, "ContactProvider::delete    " + sMatcher.match(uri));

        switch (sMatcher.match(uri))
        {
            case CONTACT_ALL:
                return db.delete("Contact", selection, selectionArgs);
            case CONTACT_ONE:
                long id = ContentUris.parseId(uri);
                String where = " id = " + Long.toString(id);
                if(selection != null && !"".equals(selection))
                {
                    where = where + " and " + selection;
                }
                return db.delete("Contact", where, selectionArgs);

            default:
                throw new IllegalArgumentException(" Unknown Uri : " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.v(ContactFragment.TAG, "ContactProvider::update()");
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Log.v(ContactFragment.TAG, "ContactProvider::update" + sMatcher.match(uri));
        switch (sMatcher.match(uri))
        {
            case CONTACT_ALL:
                return db.update("Contact", values, selection, selectionArgs);
            case CONTACT_ONE:
                long id = ContentUris.parseId(uri);
                String where = " id = " + Long.toString(id);
                if(selection != null && !"".equals(selection))
                {
                    where = where + " and " + selection;
                }
                return db.update("Contact", values, where , selectionArgs);

            default:
                throw new IllegalArgumentException(" Unknown Uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(ContactFragment.TAG, "ContactProvider::query()");
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Log.v(ContactFragment.TAG, "ContactProvider::query" + sMatcher.match(uri));
        switch (sMatcher.match(uri))
        {
            case CONTACT_ALL:
                return db.query("Contact", projection, selection, selectionArgs, null, null, sortOrder);
            case CONTACT_ONE:
                long id = ContentUris.parseId(uri);
                String where = " id = " + Long.toString(id);
                if(selection != null && !"".equals(selection))
                {
                    where = where + " and " + selection;
                }
                return db.query("Contact", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown Uri : " + uri);
        }
    }
}
