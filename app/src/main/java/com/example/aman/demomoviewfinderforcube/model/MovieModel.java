package com.example.aman.demomoviewfinderforcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aman on 21-04-2017.
 */

public class MovieModel {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Genre")
    @Expose
    private String genere;
    @SerializedName("Released")
    @Expose
    private String releasedate;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("imdbRating")
    @Expose
    private String rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
