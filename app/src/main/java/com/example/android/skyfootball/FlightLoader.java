package com.example.android.skyfootball;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class FlightLoader extends AsyncTaskLoader<List<Flight>> {

    private String url;
    public FlightLoader(Context context, String url){
        super(context);
        this.url=url;
    }

    protected void onStartLoading(){
        Log.i(LOG_TAG, "FlightLoader onStartLoading() called");
        forceLoad();
    }
    @Override
    public List<Flight> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground Called");
        ArrayList<Flight> result = QueryUtils.fetchFlightData(url);
        return result;
    }



}
