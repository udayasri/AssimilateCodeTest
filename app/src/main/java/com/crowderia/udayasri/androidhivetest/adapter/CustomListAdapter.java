package com.crowderia.udayasri.androidhivetest.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.crowderia.udayasri.androidhivetest.R;
import com.crowderia.udayasri.androidhivetest.model.Customer;
import com.crowderia.udayasri.androidhivetest.util.CommonMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomListAdapter Class
 * Created by UdayaSri on 12/21/15.
 */
public class CustomListAdapter extends BaseAdapter implements Filterable {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Customer> customerItems;

    private ArrayList<Customer> mOriginalValues; // Original Values
    private ArrayList<Customer> mDisplayedValues;    // Values to be displayed

    public CustomListAdapter(Activity activity, List<Customer> customerItems) {
        this.activity = activity;
        this.customerItems = customerItems;

        this.mOriginalValues = (ArrayList<Customer>) customerItems;
        this.mDisplayedValues = (ArrayList<Customer>) customerItems;
    }

    /*
    * Get the number of items
    * */
    @Override
    public int getCount() {
        return customerItems.size();
    }

    /*
    * Get item location
    * */
    @Override
    public Object getItem(int location) {
        return customerItems.get(location);
    }

    /*
    * Get item id
    * */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
    * get the view & bind the data to the view
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView fullname = (TextView) convertView.findViewById(R.id.fullname);
        TextView thumbnail = (TextView) convertView.findViewById(R.id.thumbnail);

        // getting customer data for the row
        Customer m = customerItems.get(position);

        fullname.setText(m.getFirst_name() + " " + m.getLast_name());

        String thumbnailText = ( String.valueOf(CommonMethods.getFirstCharacter(m.getFirst_name())) ) + ( String.valueOf(CommonMethods.getFirstCharacter(m.getLast_name())) ) ;

        thumbnail.setText(thumbnailText);

        return convertView;
    }


    /*
    * Filter the customer list according the user search
    * */
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<Customer>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Customer> FilteredArrList = new ArrayList<Customer>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Customer>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getFirst_name();
                        if (data.toLowerCase().startsWith(constraint.toString())) {

                            FilteredArrList.add(new Customer(mOriginalValues.get(i).getFirst_name(),mOriginalValues.get(i).getLast_name(),mOriginalValues.get(i).getEmail(),mOriginalValues.get(i).getThumbNail()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}