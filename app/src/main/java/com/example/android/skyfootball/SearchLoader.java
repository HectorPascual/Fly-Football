package com.example.android.skyfootball;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;
import static com.example.android.skyfootball.QueryUtils.fetchFlightData;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class SearchLoader extends AsyncTaskLoader<List<Team>> {
    private String url;
    public SearchLoader(Context context, String url){
        super(context);
        this.url=url;
    }

    protected void onStartLoading(){
        Log.i(LOG_TAG, "SearchLoader onStartLoading() called");
        forceLoad();
    }
    @Override
    public List<Team> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground Called");
        ArrayList<Team> result = QueryUtils.fetchTeamsData(url);
        return result;
    }
}
