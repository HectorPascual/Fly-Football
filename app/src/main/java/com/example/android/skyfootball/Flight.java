package com.example.android.skyfootball;

/**
 * Created by hector on 14/10/17.
 */

public class Flight {

    private int hour;
    private String fromCity;
    private String toCity;
    private double price;
    private String company;

    public Flight(int hour,String fromCity,String toCity,double price,String company){

        this.hour = hour;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.company = company;
    }

    public int getHour() {
        return hour;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }
}
