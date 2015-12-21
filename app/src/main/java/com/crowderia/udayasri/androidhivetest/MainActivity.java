package com.crowderia.udayasri.androidhivetest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.crowderia.udayasri.androidhivetest.activities.CustomerDetail;
import com.crowderia.udayasri.androidhivetest.adapter.CustomListAdapter;
import com.crowderia.udayasri.androidhivetest.app.AppController;
import com.crowderia.udayasri.androidhivetest.contentProvider.CustomerProvider;
import com.crowderia.udayasri.androidhivetest.database.DbUtils;
import com.crowderia.udayasri.androidhivetest.model.Customer;
import com.crowderia.udayasri.androidhivetest.util.CommonMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Customer MainActivity Class
 * Created by UdayaSri on 12/21/15.
 */

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Customer json url
    private static final String url = "http://alanihre.se/assimilate_code_test/?page=";
    private ProgressDialog pDialog;
    private List<Customer> customerList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;

    ArrayList<String> customer_emails = new ArrayList<>();

    private String currentPage;
    private int lastPage = 1 ;

    boolean isLoading=false;

    // Initially this will be 0, but will be updated while parsing the json
    private int page = 1;

    // Search EditText
    EditText inputSearch;

    /*
    * Method to handle the list view selection , scroll down
    * When user selects a customer direct the user in to customer details view
    * When user scroll down , load the new data from the remote server
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // find the list view by id
        listView = (ListView) findViewById(R.id.list);

        // When user select one customer from the customer list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String fullname = (String) ((TextView) view.findViewById(R.id.fullname)).getText();
                String thumbnail = (String) ((TextView) view.findViewById(R.id.thumbnail)).getText();

                Intent intent = new Intent(getApplicationContext(), CustomerDetail.class);
                intent.putExtra("fullname", fullname);
                intent.putExtra("thumbnail", thumbnail);
                intent.putExtra("email", customer_emails.get(position));

                startActivity(intent);
            }
        });

        // When user scroll down the customer list
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // does not do anything
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                Log.i("Main", totalItemCount + "");
                int lastIndexInScreen = visibleItemCount + firstVisibleItem;

                Log.d("last index", String.valueOf(lastIndexInScreen));

                if (lastIndexInScreen >= totalItemCount && !isLoading) {
                    isLoading = true;

                    //Log.d("load more data", "true");
                    // load more data
                    if(CommonMethods.isNetworkAvailable() == true){
                        makeJsonObjectRequest();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
                        //Log.d("Sorry", "No Internet Connection");
                    }

                }
            }
        });

        // assign the list to adapter
        adapter = new CustomListAdapter(this, customerList);
        listView.setAdapter(adapter);

        // find the input search
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // When user try to search by typing text
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //send the string to adapter & get the results
                adapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * Method to make json object request & pass the values to customListAdapter
     * Saving the retrieved json data to sqlite database
     * */
    private void makeJsonObjectRequest() {

        pDialog = new ProgressDialog(this);

        // Showing progress dialog before making http request
        pDialog.setMessage("Loading Customers...");
        pDialog.show();

        String json_url = url + page;

        if(page < lastPage){
            ++page ;

            //Log.d("page number", String.valueOf(page));
        }else{
            //Log.d("completed","Retrive all the pages");
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                json_url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                HashMap<String , Object> temp;

                //Log.d("Response",response.toString());

                try {

                    JSONObject meta = response.getJSONObject("meta");
                    JSONObject paging = meta.getJSONObject("paging");
                    currentPage = paging.getString("current");
                    lastPage    = Integer.parseInt(paging.getString("last"));


                    JSONArray dataArray = response.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {
                        try {

                            temp=new HashMap<String, Object>();

                            JSONObject obj = dataArray.getJSONObject(i);

                            temp.put("firstname",obj.getString("first_name"));

                            Customer customerData = new Customer();

                            customerData.setFirst_name(obj.getString("first_name"));
                            customerData.setLast_name(obj.getString("last_name"));
                            customerData.setEmail(obj.getString("email"));

                            customer_emails.add( obj.getString("email") );
                            customerList.add(customerData);

                            //save customers to the database
                            DbUtils.insertCustomers(customerData, CustomerProvider.CONTENT_URI_CUSTOMERS);



                        } catch (JSONException e) {
                            Log.d("TAG:", "error");
                            e.printStackTrace();
                        }

                    }

                    isLoading=false;

                    adapter.notifyDataSetChanged();

                    Log.d("Current Page ", String.valueOf(page));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                hidePDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    /*
    * Destroy the progress dialog
    * */
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    /*
    * Show the progress dialog
    * */
    private void showpDialog(){
        pDialog.show();
    }

    /*
    * Hide the progress dialog
    * */
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    /*
    * Menu creation - settings menu etc
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
