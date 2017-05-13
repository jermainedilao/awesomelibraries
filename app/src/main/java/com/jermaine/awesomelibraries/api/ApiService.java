package com.jermaine.awesomelibraries.api;


import com.jermaine.awesomelibraries.api.response.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/users/JakeWharton/repos")
    Call<List<Repo>> getRepos();
}