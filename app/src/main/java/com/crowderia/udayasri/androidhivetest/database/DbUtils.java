package com.crowderia.udayasri.androidhivetest.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.crowderia.udayasri.androidhivetest.app.AppController;
import com.crowderia.udayasri.androidhivetest.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * DBUtils Class
 * Created by UdayaSri on 12/21/15.
 */

public class DbUtils {

    // Insert customer details in to the sqlite database
    public static void insertCustomers(Customer singleCustomerObject,Uri uri ){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.CUSTOMER_FIRST_NAME, singleCustomerObject.getFirst_name());
        contentValues.put(DatabaseHelper.CUSTOMER_LAST_NAME, singleCustomerObject.getLast_name());
        contentValues.put(DatabaseHelper.CUSTOMER_EMAIL, singleCustomerObject.getEmail());

        AppController.getInstance().getContentResolver().insert(uri, contentValues);
    }

    // Update the customers
    public static void updateCustomers(Customer singleCustomerObject) {

    }

    // Remove customers
    public static void removeCustomers(Customer singleCustomerObject) {

    }


    // get the customer list from database
    public static List<Customer> getCustomerListFromDatabase(String selection, Uri uri) {

        Cursor cursor = AppController.getInstance().getContentResolver().query(uri,

                new String[]{
                        DatabaseHelper._CUSTOMER_ID,
                        DatabaseHelper.CUSTOMER_FIRST_NAME,
                        DatabaseHelper.CUSTOMER_LAST_NAME,
                        DatabaseHelper.CUSTOMER_EMAIL

                }, selection, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            List<Customer> customerDataList = new ArrayList<Customer>();
            if (cursor.moveToFirst()) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    customerDataList.add(createCustomerByCursor(cursor));
                    cursor.moveToNext();
                }
                return customerDataList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    //Cursor object containing the results of the query
    private static Customer createCustomerByCursor(Cursor cursor) {
        Customer customerData = new Customer();

        customerData.setFirst_name(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CUSTOMER_FIRST_NAME)));
        customerData.setLast_name(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CUSTOMER_LAST_NAME)));
        customerData.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CUSTOMER_EMAIL)));

        return customerData;
    }
}
