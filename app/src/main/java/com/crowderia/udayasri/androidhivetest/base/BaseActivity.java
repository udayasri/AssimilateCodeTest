//package com.crowderia.udayasri.androidhivetest.base;
//
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.IntentFilter;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//import com.crowderia.udayasri.androidhivetest.listner.Listeners;
//import com.crowderia.udayasri.androidhivetest.util.CommonMethods;
//
///**
// * Base Avtivity Class
// * Handles all the basic activities seprate from the Main Thread
// * Created by UdayaSri on 12/21/15.
// */
//public class BaseActivity extends AppCompatActivity implements Listeners.OnNetworkStateListener, Listeners.OnRetryClickListener {
//
//
//    private final IntentFilter mIFNetwork = new IntentFilter();
//    private final NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver(this);
//    private Dialog dlgConnectionRetry, dlgRequestRetry;
//    private ProgressDialog progress;
//
//    private boolean isNotAStartActivityCall = false;
//    protected static boolean isBackPressed;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        isNotAStartActivityCall = true;
//        init();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        isNotAStartActivityCall = false;
//        isBackPressed = false;
//        dismissProgress();
//        dismissConnectionRetry();
//        dismissRequestRetry();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (!isNotAStartActivityCall && !isBackPressed)
//            appResumedFromBackground();
//
//        mIFNetwork.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION); //"android.net.conn.CONNECTIVITY_CHANGE"
//        registerReceiver(networkStateReceiver, mIFNetwork);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(networkStateReceiver);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        isNotAStartActivityCall = true;
//        dismissConnectionRetry();
//    }
//
//    @Override
//    public void onNetworkStateChanged(NetworkInfo.State state) {
//        System.out.println("onNetworkStateChanged: " + state.toString());
//    }
//
//    @Override
//    public void onConnected() {
//        System.out.println("onConnected");
//        dismissConnectionRetry();
//    }
//
//    @Override
//    public void onDisconnected() {
//        System.out.println("onDisconnected");
//        showConnectionRetry();
//    }
//
//    @Override
//    public void onRetryClick() {
//        dismissConnectionRetry();
//        System.out.println("On Retry clicked");
//        if (!CommonMethods.isNetworkAvailable()) {
//            showConnectionRetry();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        isBackPressed = true;
//    }
//
//    @Override
//    public void onServerRequestFailed(Exception e) {
//        System.out.println("Failed");
//        dlgRequestRetry = UtilDialog.showRequestFailedRetryDialog(this, this);
//        showRequestRetry();
//    }
//
//    private void init() {
//        initConnectionRetryDialog();
//    }
//
//    private void initConnectionRetryDialog() {
//        if (dlgConnectionRetry == null) {
//            dlgConnectionRetry = UtilDialog.showConnectionRetryDialog(this, this);
//            if (!CommonMethods.isNetworkAvailable())
//                showConnectionRetry();
//        }
//    }
//
//    public void showConnectionRetry() {
//        initConnectionRetryDialog();
//        if (!dlgConnectionRetry.isShowing()) {
//            dlgConnectionRetry.show();
//        }
//    }
//
//    private void dismissConnectionRetry() {
//        if (dlgConnectionRetry != null) {
//            dlgConnectionRetry.dismiss();
//            dlgConnectionRetry = null;
//        }
//    }
//
//    public void showRequestRetry() {
//        initConnectionRetryDialog();
//        if (!dlgRequestRetry.isShowing()) {
//            dlgRequestRetry.show();
//        }
//    }
//
//    public void showProgress(String message) {
//        if (progress == null || !progress.isShowing()) {
//            progress = ProgressDialog.show(this, null, message, true);
//        }
//    }
//
//    public void dismissProgress() {
//        if (progress != null && progress.isShowing())
//            progress.dismiss();
//    }
//
//
//    private void dismissRequestRetry() {
//        if (dlgRequestRetry != null) {
//            dlgRequestRetry.dismiss();
//            dlgRequestRetry = null;
//        }
//    }
//
//    public void appResumedFromBackground() {
//        System.out.println("appResumedFromBackground");
//    }
//
//}
