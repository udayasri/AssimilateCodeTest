//package com.crowderia.udayasri.androidhivetest.listner;
//
//import android.net.NetworkInfo;
//import android.view.View;
//
//import com.crowderia.udayasri.androidhivetest.base.BaseListener;
//
///**
// * Listener Class - listen to recyler view on click / network status / retry click / customer data fetch
// * Created by UdayaSri on 12/21/15.
// */
//public class Listeners {
//
//    public interface RecyclerViewOnClickListener {
//        public void onRecyclerViewOnClick(View view, int index);
//    }
//
//    public interface OnNetworkStateListener extends BaseListener {
//
//        void onNetworkStateChanged(NetworkInfo.State state);
//
//        void onConnected();
//
//        void onDisconnected();
//    }
//
//    public interface OnRetryClickListener extends BaseListener {
//        void onRetryClick();
//    }
//
//    public interface OnCustomerFetchListener extends BaseListener {
//        void OnCustomerFetch();
//}
