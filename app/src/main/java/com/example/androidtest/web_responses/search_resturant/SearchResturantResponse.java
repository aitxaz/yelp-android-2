
package com.example.androidtest.web_responses.search_resturant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResturantResponse {

    @SerializedName("businesses")
    @Expose
    private ArrayList<Business> businesses;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("region")
    @Expose
    private Region region;

    public ArrayList<Business> getBusinesses() {

        if (businesses != null) {
            return businesses;
        } else {
            return new ArrayList<>();
        }
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
