//package com.crowderia.udayasri.androidhivetest.fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v4.util.Pair;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.crowderia.udayasri.androidhivetest.R;
//import com.crowderia.udayasri.androidhivetest.activities.CustomerDetail;
//import com.crowderia.udayasri.androidhivetest.base.BaseFragment;
//import com.crowderia.udayasri.androidhivetest.contentProvider.CustomerProvider;
//import com.crowderia.udayasri.androidhivetest.database.DbUtils;
//import com.crowderia.udayasri.androidhivetest.model.Customer;
//import com.crowderia.udayasri.androidhivetest.util.Constants;
//
//import java.util.List;
//
///**
// * Created by UdayaSri on 12/21/15.
// */
//public class FragmentCustomers extends BaseFragment implements Listeners.OnRetryClickListener {
//
//    private RecyclerView mRecyclerView;
//    private List<Customer> mCustomerList;
//    private View mMainContent;
//
//    public FragmentCustomers() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        sendRequest();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        mMainContent = inflater.inflate(R.layout.fragment_customer, container, false);
//        mRecyclerView = (RecyclerView) mMainContent.findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(layoutManager);
//        return mMainContent;
//    }
//
//    @Override
//    public void OnCustomerFetch() {
//        setAdapterToRecyclerView();
//    }
//
//    private void sendRequest() {
//        ((BaseActivity) getActivity()).showProgress(getString(R.string.msg_fetching_customers));
//        RequestSender.getCustomers(this, Constants.URL_CUSTOMERS );
//    }
//
//    private void setAdapterToRecyclerView() {
//        mCustomerList = DbUtils.getCustomerListFromDatabase(ListFromDatabase(CustomerProvider.CONTENT_URI_CUSTOMERS);
//        if(mCustomerList!=null){
//            mRecyclerView.setAdapter(new AdapterCustomer(mCustomerList, getActivity(), this));
//        }
//        ((BaseActivity) getActivity()).dismissProgress();
//    }
//
//    @Override
//    public void onServerRequestFailed(Exception e) {
//        setAdapterToRecyclerView();
//        showRequestRetrySnackBar(mMainContent);
//    }
//
//    @Override
//    public void onRetryClick() {
//        sendRequest();
//    }
//
//    @Override
//    public void onRecyclerViewOnClick(View view, int index) {
//        Intent intent = new Intent(getActivity(), CustomerDetail.class);
//        intent.putExtra(Constants.KEY_CUSTOMER_ID, mCustomerList.get(index).getId() + "");
//        //start activity with transition animation
//        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//    }
//}
