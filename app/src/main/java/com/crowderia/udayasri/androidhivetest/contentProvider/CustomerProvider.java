package com.crowderia.udayasri.androidhivetest.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.crowderia.udayasri.androidhivetest.database.DatabaseHelper;

/**
 * Created by UdayaSri on 12/21/15.
 */
public class CustomerProvider extends ContentProvider {

    private static final String BASE_PATH = "customers";
    private static final String AUTHORITY = "com.crowderia.udayasri.androidhivetest.contentProvider.CustomerProvider.customers";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final Uri CONTENT_URI_CUSTOMERS = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private DatabaseHelper dbHelper;

    // Used for the UriMacher
    private static final int CUSTOMERS = 10;

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, CUSTOMERS);
    }

    @Override
    public boolean onCreate() {
        dbHelper = DatabaseHelper.getInstance(getContext());
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = sURIMatcher.match(uri);

        switch (uriType) {

            case CUSTOMERS:
                queryBuilder.setTables(DatabaseHelper.TABLE_CUSTOMER);
                break;
            default:
                break;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        // Make sure that potential listeners are getting notified
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insertWithOnConflict(DatabaseHelper.TABLE_CUSTOMER, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE_CUSTOMER, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.update(DatabaseHelper.TABLE_CUSTOMER, values, selection, selectionArgs);
    }
}

