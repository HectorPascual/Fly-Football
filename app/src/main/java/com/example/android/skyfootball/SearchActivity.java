package com.example.android.skyfootball;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<Team>>{

    private Button searchButton;
    private TextView teamTextView, cityTextView;
    private SearchLoader searchLoader;
    private String url="http://api.football-data.org/v1/competitions/464/teams/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        searchButton = (Button) findViewById(R.id.search_button);
        teamTextView = (TextView) findViewById(R.id.team_edit_text);
        cityTextView = (TextView) findViewById(R.id.city_edit_text);

        searchButton.setOnClickListener(this);

        if(checkInternet()!=null && checkInternet().isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1,null,this);
        }



    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.search_button){
            Intent intent = new Intent(SearchActivity.this, MatchesActivity.class);
            intent.putExtra("ourTeam", teamTextView.getText().toString());
            intent.putExtra("ourCity", cityTextView.getText().toString());

            startActivity(intent);
        }
    }

    @Override
    public Loader<List<Team>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader Called");
        return new SearchLoader(SearchActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Team>> loader, List<Team> data) {
        Log.i(LOG_TAG, "onLoadFinished Called");
        if (data != null && !data.isEmpty()) {
            Data.fillTeams((ArrayList<Team>) data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Team>> loader) {
        Data.fillTeams(null);
    }
    public NetworkInfo checkInternet(){
        ConnectivityManager connMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMan.getActiveNetworkInfo();

    }
}
