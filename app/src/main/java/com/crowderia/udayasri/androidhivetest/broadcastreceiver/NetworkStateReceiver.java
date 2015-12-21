//package com.crowderia.udayasri.androidhivetest.broadcastreceiver;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.SystemClock;
//import android.util.Log;
//
///**
// * Created by UdayaSri on 12/21/15.
// */
//public class NetworkStateReceiver extends BroadcastReceiver {
//
//    private long mLastNotifiedime = 0;
//    private Listeners.OnNetworkStateListener networkStateListener;
//
//    public NetworkStateReceiver() {}
//
//    public NetworkStateReceiver(Listeners.OnNetworkStateListener networkStateListener) {
//        this.networkStateListener = networkStateListener;
//    }
//
//    public void onReceive(Context context, Intent intent) {
//        Log.d("app", "Network connectivity change");
//        if (SystemClock.elapsedRealtime() - mLastNotifiedime < 1000) {
//            return;
//        }
//        mLastNotifiedime = SystemClock.elapsedRealtime();
//
//        if (intent.getExtras() != null) {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo ni = cm.getActiveNetworkInfo();
//            if (ni != null) {
//                if (networkStateListener != null)
//                    networkStateListener.onNetworkStateChanged(ni.getState());
//            }
//            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
//                if (networkStateListener != null)
//                    networkStateListener.onConnected();
//                Log.i("app", "Network " + ni.getTypeName() + " connected");
//            } else if (ni != null && ni.getState() == NetworkInfo.State.DISCONNECTED) {
//                if (networkStateListener != null)
//                    networkStateListener.onDisconnected();
//            }
//        }
//        if (intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
//            Log.d("app", "There's no network connectivity");
//            if (networkStateListener != null)
//                networkStateListener.onDisconnected();
//        }
//}
