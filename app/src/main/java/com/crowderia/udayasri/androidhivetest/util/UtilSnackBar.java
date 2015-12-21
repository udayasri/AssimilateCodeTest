//package com.crowderia.udayasri.androidhivetest.util;
//
//import android.support.design.widget.Snackbar;
//import android.view.View;
//
///**
// * Created by UdayaSri on 12/21/15.
// */
//public class UtilSnackBar {
//
//    public static Snackbar createSnackBarRequestRetry(View view, final Listeners.OnRetryClickListener retryClick){
//
//        Snackbar snackbar=Snackbar.make(view, R.string.msg_request_retry, Snackbar.LENGTH_LONG).setAction("Action", null);
//
//        snackbar.setAction(R.string.btnRetry, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (retryClick != null)
//                    retryClick.onRetryClick();
//            }
//        });
//        return snackbar;
//    }
//
//}
