package com.hernandez.mickael.mynews.interfaces

import com.hernandez.mickael.mynews.models.Article
import com.hernandez.mickael.mynews.models.Result
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mickael Hernandez on 07/11/2017.
 */
internal interface MostPopularInterface {

    @GET("mostpopular/v2/mostviewed/{section}/1.json")
    fun all(): Call<Result>

    @GET("mostpopular/v2/mostviewed/{section}/1.json")
    fun select(section: String): Call<Article>
}

