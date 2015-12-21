package com.crowderia.udayasri.androidhivetest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.crowderia.udayasri.androidhivetest.R;

/**
 * Created by UdayaSri on 12/19/15.
 * Customer Detail Class
 */
public class CustomerDetail extends AppCompatActivity {

    /*
    * Customer Details view on create method
    * Retrive the data from intent & show it in the text views
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();

        if (intent != null){

            TextView fullname = (TextView) findViewById(R.id.fullname);
            TextView thumbnail = (TextView)findViewById(R.id.thumbnail);
            TextView email     = (TextView) findViewById(R.id.email);

            fullname.setText(intent.getStringExtra("fullname"));
            thumbnail.setText(intent.getStringExtra("thumbnail"));
            email.setText(intent.getStringExtra("email"));

            toolbar.setTitle( intent.getStringExtra("fullname") );
        }else{
            toolbar.setTitle("Customer Details");
        }

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send email to the customer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
