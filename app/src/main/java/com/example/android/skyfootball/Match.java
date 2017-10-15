package com.example.android.skyfootball;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class Match {
    private String date;
    private int hour;
    private String city;
    private String idCity;
    private String homeTeam;
    private String awayTeam;
    private double minPrice;
    private String status;
    private int goalsHome;
    private int goalsAway;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public Match(String date, int hour, String team1, String team2, String status){
        this.hour=hour;
        this.homeTeam =team1;
        this.awayTeam =team2;
        this.date=date;
        this.status = status;

    }
    public Match(){

    }


    public int getHour() {
        return hour;
    }

    public String getCity() {
        return city;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getMinPrice() {
        return (int)minPrice;
    }

    public String getIdCity(){ return idCity;}

    public String getDate(){ return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
