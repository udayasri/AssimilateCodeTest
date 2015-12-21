package com.crowderia.udayasri.androidhivetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper Class
 * Created by UdayaSri on 12/21/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    // Database attributes
    public static final String DB_NAME = "customer_db";
    public static final int DB_VERSION = 1;

    // Customer table attributes
    public static final String TABLE_CUSTOMER = "customer_table";
    public static final String _CUSTOMER_ID = "_id";
    public static final String CUSTOMER_FIRST_NAME = "first_name";
    public static final String CUSTOMER_LAST_NAME = "last_name";
    public static final String CUSTOMER_EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }

    /*
    * Hanldes the data base creation
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQueryToCreateCustomerTable = "create table if not exists "

                + TABLE_CUSTOMER +
                " ( "

                + _CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CUSTOMER_FIRST_NAME + " TEXT , "
                + CUSTOMER_LAST_NAME + " TEXT , "
                + CUSTOMER_EMAIL + " TEXT  "

                + ");";

        db.execSQL(sqlQueryToCreateCustomerTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion >= 2) {
            // TODO handle database upgrade
        }
    }

    public static DatabaseHelper getInstance(Context context) {

        return new DatabaseHelper(context);
    }
}
