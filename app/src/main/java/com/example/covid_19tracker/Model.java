package com.example.covid_19tracker;

public class Model {
    private String state,dist;
    private String active,confirmed,deceased,recovered;


    public Model(String state, String dist, String active, String confirmed, String deceased, String recovered) {
        this.state = state;
        this.dist = dist;
        this.active = active;
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.recovered = recovered;
    }


    public String getState() {
        return state;
    }

    public String getDist() {
        return dist;
    }

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getRecovered() {
        return recovered;
    }


}
