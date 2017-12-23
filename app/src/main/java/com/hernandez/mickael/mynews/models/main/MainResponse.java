package com.hernandez.mickael.mynews.models.main;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** Class used to parse Most Popular and Top Stories API Json response */
public class MainResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName(value="results")
    @Expose
    private ArrayList<Article> mArticles = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.mArticles = articles;
    }

}