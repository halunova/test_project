package ru.startandroid.testproject.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.startandroid.testproject.api.GithubAPI;

/**
 * Created by Виктория on 02.03.2018.
 */

public class Controller {
    static final String BASE_URL = "https://api.github.com";

    public static GithubAPI getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubAPI api = retrofit.create(GithubAPI.class);
        return api;
    }

    public static GithubAPI getApi(String BASE_URL) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubAPI api = retrofit.create(GithubAPI.class);
        return api;
    }
}

