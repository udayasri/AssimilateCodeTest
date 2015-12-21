//package com.crowderia.udayasri.androidhivetest.base;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * Created by UdayaSri on 12/21/15.
// */
//public class BaseFragment extends Fragment implements Listeners.OnRetryClickListener {
//
//    private Dialog mDialogRequestRetry;
//    private Snackbar mSnackbarRequestRetry;
//
//    public BaseFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        return textView;
//    }
//
//
//    @Override
//    public void onRetryClick() {
//
//    }
//
//    @Override
//    public void onServerRequestFailed(Exception e) {
//
//    }
//
//
//    private void initRequestRetrySnackBar(View view) {
//        mSnackbarRequestRetry = UtilSnackBar.createSnackBarRequestRetry(view, this);
//    }
//
//    public void showRequestRetrySnackBar(View view) {
//        initRequestRetrySnackBar(view);
//        if (!mSnackbarRequestRetry.isShownOrQueued()) {
//            mSnackbarRequestRetry.show();
//        }
//    }
//}
