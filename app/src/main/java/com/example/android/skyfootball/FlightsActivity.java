package com.example.android.skyfootball;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;

public class FlightsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Flight>>{
    private String url;
    private FlightAdapter flightAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        ListView listView = (ListView) findViewById(R.id.flight_list);

        final ArrayList<Flight> flights = new ArrayList<>();
        flights.add(new Flight(12,"Barcelona","Munich",132.50,"Ryanair"));
        flights.add(new Flight(16,"Paris","Manchester",156.80,"KatarAirways"));
        flights.add(new Flight(18,"London","Roma",110.0,"Iberia"));
        flights.add(new Flight(12,"Barcelona","Munich",132.50,"Ryanair"));
        flights.add(new Flight(16,"Paris","Manchester",156.80,"KatarAirways"));
        flights.add(new Flight(18,"London","Roma",110.0,"Iberia"));
        flights.add(new Flight(12,"Barcelona","Munich",132.50,"Ryanair"));
        flights.add(new Flight(16,"Paris","Manchester",156.80,"KatarAirways"));
        flights.add(new Flight(18,"London","Roma",110.0,"Iberia"));
        flights.add(new Flight(12,"Barcelona","Munich",132.50,"Ryanair"));
        flights.add(new Flight(16,"Paris","Manchester",156.80,"KatarAirways"));
        flights.add(new Flight(18,"London","Roma",110.0,"Iberia"));

        flightAdapter = new FlightAdapter(this,R.layout.flight_item,flights);

        listView.setAdapter(flightAdapter);

    }

    @Override
    public Loader<List<Flight>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader Called");
        return new FlightLoader(FlightsActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Flight>> loader, List<Flight> data) {
        Log.i(LOG_TAG, "onLoadFinished Called");
        flightAdapter.clear();
        if (data != null && !data.isEmpty()) {
            flightAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Flight>> loader) {
        flightAdapter.clear();
    }
}
