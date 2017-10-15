package com.example.android.skyfootball;

/**
 * Created by Albert Risco on 14/10/2017.
 */

public class Team {
    private String ref;
    private String name;
    private String city;
    private String url_logo;

    public Team(String name, String url_logo){
        this.name=name;
        this.url_logo=url_logo;
    }

    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }
}
