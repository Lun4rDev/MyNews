package com.hernandez.mickael.mynews.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hernandez.mickael.mynews.utils.MediumAdapterFactory;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dw on 22/02/17.
 */

public class ApiSingleton {

    private static ApiInterface mInstance = null;

    private ApiSingleton() {};

    public static ApiInterface getInstance() {
        if (mInstance == null) {
            mInstance = getRetrofit().create(ApiInterface.class);
        }
        return mInstance;
    }

    private static Retrofit getRetrofit() {

        // Customise Gson instance
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                //.registerTypeAdapter(Medium.class, new ArticleDeserializerJson())
                .registerTypeAdapterFactory(new MediumAdapterFactory())
                //.registerTypeAdapter(Medium.class, new MediumTypeAdapter())
                .create();

        // Debug interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Append api-key parameter to every query
        Interceptor apiKeyInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", ApiInterface.API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(interceptor)  // Enable debugging
                .build();

        // Create Retrofit instance
        return new Retrofit.Builder()
                .baseUrl(ApiInterface.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}