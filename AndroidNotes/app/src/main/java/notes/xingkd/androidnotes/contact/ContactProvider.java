package notes.xingkd.androidnotes.contact;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

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
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (sMatcher.match(uri))
        {
            case CONTACT_ALL:
                SQLiteDatabase db = mDBHelper.getReadableDatabase();
                return db.query("Contact", projection, selection, selectionArgs, null, null, null);
            case CONTACT_ONE:

                break;
            default:
                break;
        }
        return null;
    }
}
