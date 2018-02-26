package com.example.roline.rural_hackathon1;

/**
 * Created by Roline on 21-02-2018.
 */

public class User {

    String region;
    int total;

    public  User(String region, int total)
    {
        this.region=region;
        this.total=total;

    }

    public String getRegion()
    {
        return  region;
    }
    public int getTotal()
    {
        return total;
    }


}
