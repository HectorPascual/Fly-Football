package com.example.android.skyfootball;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hector on 14/10/17.
 */

public class FlightAdapter extends ArrayAdapter<Flight>{

    public FlightAdapter(Activity context,int resource, ArrayList<Flight> flights){
        super(context,resource,flights);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.flight_item,parent,false);
        }
        Flight currentFlight = getItem(position);

        TextView hourOfFlight = (TextView) listItemView.findViewById(R.id.hourOfFlight);
        hourOfFlight.setText(String.valueOf(currentFlight.getHour()));

        TextView companyOfFlight = (TextView) listItemView.findViewById(R.id.companyOfFlight);
        companyOfFlight.setText(currentFlight.getCompany());

        TextView priceOfFlight = (TextView) listItemView.findViewById(R.id.priceOfFlight);
        priceOfFlight.setText(String.valueOf(currentFlight.getPrice()));

        return listItemView;

    }




}
