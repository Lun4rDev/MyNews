package com.hernandez.mickael.mynews.api;

import com.hernandez.mickael.mynews.models.main.MainResponse;
import com.hernandez.mickael.mynews.models.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by Mickael Hernandez on 22/02/17.
 */

/** NYTimes API interface */
public interface ApiInterface {

    /** NYT API Key */
    String API_KEY = "79267985f9cc49558b809254d951d64c";

    /** API base URL */
    String API_BASE_URL = "https://api.nytimes.com/svc/";

    /** Most Popular API call */
    @GET("mostpopular/v2/mostviewed/all-sections/1.json")
    Call<MainResponse> mostPopular();

    /** Top Stories API call */
    @GET("topstories/v2/world.json")
    Call<MainResponse> topStories();

    /** Article Search API call */
    @GET("search/v2/articlesearch.json")
    Call<SearchResponse> articleSearch(
            @Query("q") String query,
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("fq") String sections);

    /** Article Search API call used for the section tabs */
    @GET("search/v2/articlesearch.json")
    Call<SearchResponse> section(
            @Query("fq") String sections);

}