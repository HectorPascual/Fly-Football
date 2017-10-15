package com.example.android.skyfootball;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.R.attr.format;
import static android.R.attr.id;
import static com.example.android.skyfootball.MatchesActivity.LOG_TAG;
import static okhttp3.Protocol.get;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public final class QueryUtils {
    private QueryUtils(){

    }
    private static double fetchMinPriceData(String selectedCityId, String matchCityId, String date, String returnDate){

        String rawUrl = null;
        try {
            rawUrl = "http://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/US/EUR/es-ES/"+selectedCityId+"/"+matchCityId+"/"+sumarDiasAFecha(date,-1)+"/"+returnDate+"?" +
                    "apiKey=ha296511972791813076557417617660";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        URL url = createUrl(rawUrl);
        String jsonResponse = null;

        try{
            jsonResponse=makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractMinPrice(jsonResponse);
    }


    private static String fetchIdData(String city){
        String rawUrl="http://partners.api.skyscanner.net/apiservices/autosuggest/v1.0/US/EUR/en-UK?query="+city+"&apiKey=ha296511972791813076557417617660";
        URL url = createUrl(rawUrl);

        String jsonResponse = null;

        try{
            jsonResponse=makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractID(jsonResponse);

    }

    public static ArrayList<Match> fetchMatchData(String rawUrl, String selectedTeam, String selectedCity){
        Log.i(LOG_TAG, "fetchBooksData Called");

        URL url = createUrl(rawUrl);

        String jsonResponse = null;
        try{
            jsonResponse=makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        String selectedCityId=fetchIdData(selectedCity);

        return extractMatches(jsonResponse,selectedTeam, selectedCityId);
    }
    public static ArrayList<Flight> fetchFlightData(String rawUrl){
        Log.i(LOG_TAG, "fetchBooksData Called");

        URL url = createUrl(rawUrl);

        String jsonResponse = null;
        try{
            jsonResponse=makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractFlights(jsonResponse);
    }

    public static ArrayList<Team> fetchTeamsData(String rawUrl){
        Log.i(LOG_TAG, "fetchBooksData Called");

        URL url = createUrl(rawUrl);

        String jsonResponse = null;
        try{
            jsonResponse=makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractTeams(jsonResponse);
    }



    private static URL createUrl(String rawUrl){
        URL url =null;
        try{
            url = new URL(rawUrl);
        }catch(MalformedURLException e){
            Log.i(LOG_TAG,"Problem building the URL", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse="";

        if(url==null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream=null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the  JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    private static String readFromInputStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Match> extractMatches(String jsonResponse, String selectedTeam, String selectedCityId){
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<Team> teams = Data.getTeams();
        HashMap<Team,String> completeTeams = Data.getCompleteTeams();
        try{
            JSONObject jsonMatches = new JSONObject(jsonResponse);
            JSONArray matchesArray = jsonMatches.getJSONArray("fixtures");

            String status, homeTeam, awayTeam, date, hour, dateRaw;
            int goalsHome,goalsAway;
            JSONObject match;
            for(int i=0; i<matchesArray.length(); i++){
                match=matchesArray.getJSONObject(i);

                status=match.getString("status");

                homeTeam=match.getString("homeTeamName");
                awayTeam=match.getString("awayTeamName");
                if(homeTeam.toLowerCase().contains(selectedTeam.toLowerCase())
                        || awayTeam.toLowerCase().contains(selectedTeam.toLowerCase()))
                {
                    Match toBeInsertedMatch = new Match();
                    toBeInsertedMatch.setHomeTeam(homeTeam);
                    toBeInsertedMatch.setAwayTeam(awayTeam);

                    dateRaw = match.getString("date");
                    date=formatDate(dateRaw);
                    toBeInsertedMatch.setDate(date);
                    for(Team t:teams){
                        if(t.getName().equals(homeTeam)){
                           toBeInsertedMatch.setCity(completeTeams.get(t));
                            Log.i(LOG_TAG, ": match"+t.getName()+"  city: "+toBeInsertedMatch.getCity());
                        }
                    }
                    toBeInsertedMatch.setIdCity(fetchIdData(toBeInsertedMatch.getCity()));
                    //calculate return date
                    String returnDate = sumarDiasAFecha(date,1);
                    toBeInsertedMatch.setMinPrice(CalculateMinPrice(selectedCityId, toBeInsertedMatch.getIdCity(),
                            date, returnDate));

                    if(status.equals("SCHEDULED")) {
                        matches.add(toBeInsertedMatch);
                        toBeInsertedMatch.setStatus("SCHEDULED");
                    }
                    else{
                        JSONObject json = match.getJSONObject("result");
                        goalsHome = json.getInt("goalsHomeTeam");
                        goalsAway = json.getInt("goalsAwayTeam");
                        toBeInsertedMatch.setGoalsHome(goalsHome);
                        toBeInsertedMatch.setGoalsAway(goalsAway);
                        Log.e(LOG_TAG,"!!!"+goalsAway+"-"+goalsHome);
                        toBeInsertedMatch.setDate("FINISHED");
                        toBeInsertedMatch.setStatus("FINISHED");
                        matches.add(toBeInsertedMatch);
                    }
                }
            }
        }catch(JSONException e){
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static ArrayList<Flight> extractFlights(String jsonResponse){
        return null;
    }
    private static ArrayList<Team> extractTeams(String jsonResponse){
        ArrayList<Team> teams = new ArrayList<>();
        try{
            JSONObject JsonTeams = new JSONObject(jsonResponse);
            JSONArray teamsArray = JsonTeams.getJSONArray("teams");

            String name, url_logo;
            JSONObject team;
            for(int i=0; i<teamsArray.length(); i++){
                team=teamsArray.getJSONObject(i);
                name=team.getString("name");
                url_logo=team.getString("crestUrl");
                teams.add(new Team(name, url_logo));
                Log.i(LOG_TAG, name);
            }


        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the  JSON results", e);
        }
        return teams;
    }
    private static String extractID(String jsonResponse){
        //try{
        Log.e(LOG_TAG, jsonResponse);
        String placeId = "";
        JSONObject json1;
        JSONArray placeIdArray;
        try {
            json1 = new JSONObject(jsonResponse);
            placeIdArray = json1.getJSONArray("Places");
            placeId = (placeIdArray.getJSONObject(0)).getString("PlaceId");
            Log.e(LOG_TAG,placeId);
        }
        catch(JSONException e){
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        return placeId;
    }

    private static double extractMinPrice(String jsonResponse){
        double minPrice=0.0;
        try{
            Log.i(LOG_TAG,jsonResponse);
            JSONObject jsonPrice = new JSONObject(jsonResponse);
            JSONArray quotes = jsonPrice.getJSONArray("Quotes");
            minPrice=quotes.getJSONObject(0).getDouble("MinPrice");


        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }
        return minPrice;
    }
    private static String formatDate(String dateRaw){
        char[] dateChar = dateRaw.toCharArray();
        String formatDate="";

        for(int i=0;i<10;i++){
            formatDate = formatDate + dateChar[i];
        }
        return formatDate;
    }

    public static String sumarDiasAFecha(String dt, int days) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, days);  // number of days to add
        dt = sdf.format(c.getTime());
        return dt;
    }

    private static double CalculateMinPrice(String selectedCityId, String matchCityId,
    String date, String returnDate){
        double minPrice= fetchMinPriceData(selectedCityId, matchCityId, date, returnDate);
        return minPrice;
    }

}
