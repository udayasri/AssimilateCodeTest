//package com.crowderia.udayasri.androidhivetest.network;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//
//import org.json.JSONObject;
//
///**
// * Request Sender Class
// * Send the request for get the json result - seperate from the UI thread
// * Created by UdayaSri on 12/21/15.
// */
//public class RequestSender {
//
//    public static void getCustomers(final Listeners.OnCustomerFetchListener listener, String url, final int category) {
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JsonDecoders.decodeCustomers(response, category);
//                    listener.OnCustomerFetch();
//                } catch (Exception e) {
//                    listener.onServerRequestFailed(e);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onServerRequestFailed(error);
//            }
//        });
//        VolleySingleton.getInstance().addToRequestQueue(request);
//}
