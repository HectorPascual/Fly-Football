package com.example.android.skyfootball;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class MatchAdapter extends ArrayAdapter<Match>{

    public MatchAdapter(Activity context, int resource, ArrayList<Match> matches){
        super(context, resource, matches);


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.match_item, parent, false);
        }

        Match currentMatch = getItem(position);

        TextView team1TextView = (TextView) listItemView.findViewById(R.id.team1);
        team1TextView.setText(currentMatch.getHomeTeam());

        TextView team2TextView = (TextView) listItemView.findViewById(R.id.team2);
        team2TextView.setText(currentMatch.getAwayTeam());

        TextView hourTextView = (TextView) listItemView.findViewById(R.id.dateOfMatch);
        hourTextView.setText(String.valueOf(currentMatch.getDate()));

        TextView cityTextView = (TextView) listItemView.findViewById(R.id.cityOfMatch);
        cityTextView.setText(currentMatch.getCity());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.priceOfMatch);
        int a = (int)currentMatch.getMinPrice();

        TextView team1ScoreTextView = (TextView) listItemView.findViewById(R.id.team1_score);
        team1ScoreTextView.setText(""+currentMatch.getGoalsHome());

        TextView team2ScoreTextView = (TextView) listItemView.findViewById(R.id.team2_score);
        team2ScoreTextView.setText(""+currentMatch.getGoalsAway());

        ImageView icon1ImageView = (ImageView) listItemView.findViewById(R.id.team1_icon);
        String homeTeam = currentMatch.getHomeTeam();
        ImageView icon2ImageView = (ImageView) listItemView.findViewById(R.id.team2_icon);
        String awayTeam = currentMatch.getAwayTeam();

        Log.e(LOG_TAG, homeTeam.toLowerCase().replaceAll("\\s+", ""));
        if (homeTeam.toLowerCase().replace(" ", "").contains("fkqa")) {
            icon1ImageView.setImageResource(
                    getContext().getResources().getIdentifier("fkqarabaadam", "mipmap", getContext().getPackageName())
            );
        }
        else if(homeTeam.toLowerCase().contains("olymp")){
            icon1ImageView.setImageResource(
                    getContext().getResources().getIdentifier("olympiacosfc", "mipmap", getContext().getPackageName())
            );
        }
        else {
            icon1ImageView.setImageResource(
                    getContext().getResources().getIdentifier(homeTeam.toLowerCase().replaceAll("é", "e").replaceAll("\\s+", "").replaceAll("ü", "u").replaceAll("-", ""), "mipmap", getContext().getPackageName())
            );
        }

         if (awayTeam.toLowerCase().replace(" ", "").contains("fkqa")) {
             icon2ImageView.setImageResource(
                     getContext().getResources().getIdentifier("fkqarabaadam", "mipmap", getContext().getPackageName())
             );
         }
         else if(awayTeam.toLowerCase().contains("olymp")){
             icon2ImageView.setImageResource(
                     getContext().getResources().getIdentifier("olympiacosfc", "mipmap", getContext().getPackageName())
             );
         }
         else {
             icon2ImageView.setImageResource(
                     getContext().getResources().getIdentifier(awayTeam.toLowerCase().replaceAll("é", "e").replaceAll("\\s+", "").replaceAll("ü", "u").replaceAll("-", ""), "mipmap", getContext().getPackageName())
             );
         }

//homeTeam.toLowerCase().trim()

        //Log.e(LOG_TAG,  String.valueOf(a));
        if(a==0){
            priceTextView.setText("N/A");
        }
        else priceTextView.setText(String.valueOf(currentMatch.getMinPrice())+"€");

        if(currentMatch.getStatus().equals("SCHEDULED")){
            team1ScoreTextView.setText("");
            team2ScoreTextView.setText("");
        }

        return listItemView;

    }

}
