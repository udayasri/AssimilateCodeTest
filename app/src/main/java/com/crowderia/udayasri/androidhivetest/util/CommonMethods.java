package com.crowderia.udayasri.androidhivetest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.crowderia.udayasri.androidhivetest.app.AppController;

/**
 * Created by UdayaSri on 12/20/15.
 */
public class CommonMethods {


    /*
    * Get a string & return the first character of that string - Used for Create the thumbnail
    * */
    public static String getFirstCharacter(String name ){

        return(name.substring(0,1));
    }

    /*
    * Check for the network availablity
    * */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
