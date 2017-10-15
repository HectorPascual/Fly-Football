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

public class MatchLoader extends AsyncTaskLoader<List<Match>> {
    private String url, selectedTeam, selectedCity;

    public MatchLoader(Context context, String url, String selectedTeam, String selectedCity){
        super(context);
        this.url=url;
        this.selectedTeam=selectedTeam;
        this.selectedCity=selectedCity;
    }

    protected void onStartLoading(){
        Log.i(LOG_TAG, "MatchLoader onStartLoading() called");
        forceLoad();
    }
    @Override
    public List<Match> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground Called");
        ArrayList<Match> result = QueryUtils.fetchMatchData(url, selectedTeam, selectedCity);
        return result;
    }
}
