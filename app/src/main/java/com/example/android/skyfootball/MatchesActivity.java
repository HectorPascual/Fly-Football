package com.example.android.skyfootball;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MatchesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Match>>{
    public static final String LOG_TAG = MatchesActivity.class.getName();
    private String url = "http://api.football-data.org/v1/soccerseasons/464/fixtures";
    private static String selectedTeam, selectedCity;
    private ArrayList<Integer> resources;

    MatchAdapter matchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        ListView listView = (ListView) findViewById(R.id.match_list);

        ArrayList<Match> matches = new ArrayList<>();
        /*
        matches.add(new Match(1224, "barça", "madrid", "Barcelona", 67.54));
        matches.add(new Match(1156, "hola", "adios", "Madrid", 78.59));
        matches.add(new Match(4633, "xfff", "wuyegf", "Londres", 89.8));
        matches.add(new Match(1224, "barça", "madrid", "Barcelona", 67.54));
        matches.add(new Match(1156, "hola", "adios", "Madrid", 78.59));
        matches.add(new Match(4633, "xfff", "wuyegf", "Londres", 89.8));
        matches.add(new Match(1224, "barça", "madrid", "Barcelona", 67.54));
        matches.add(new Match(1156, "hola", "adios", "Madrid", 78.59));
        matches.add(new Match(4633, "xfff", "wuyegf", "Londres", 89.8));
        matches.add(new Match(1224, "barça", "madrid", "Barcelona", 67.54));
        matches.add(new Match(1156, "hola", "adios", "Madrid", 78.59));
        matches.add(new Match(4633, "xfff", "wuyegf", "Londres", 89.8));
        matches.add(new Match(1224, "barça", "madrid", "Barcelona", 67.54));
        matches.add(new Match(1156, "hola", "adios", "Madrid", 78.59));
        matches.add(new Match(4633, "xfff", "wuyegf", "Londres", 89.8));*/

        Intent intent = getIntent();
        selectedTeam = intent.getStringExtra("ourTeam");
        selectedCity = intent.getStringExtra("ourCity");

        matchAdapter = new MatchAdapter(this,R.layout.match_item, matches);

        listView.setAdapter(matchAdapter);


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1,null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Match match = matchAdapter.getItem(position);
                Intent intent = new Intent(MatchesActivity.this, FlightsActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public Loader<List<Match>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader Called");
        return new MatchLoader(MatchesActivity.this, url, selectedTeam, selectedCity);
    }

    @Override
    public void onLoadFinished(Loader<List<Match>> loader, List<Match> data) {
        Log.i(LOG_TAG, "onLoadFinished Called");
        matchAdapter.clear();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            matchAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Match>> loader) {
        matchAdapter.clear();
    }
}
