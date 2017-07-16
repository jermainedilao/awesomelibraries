package com.jermaine.awesomelibraries.api


import com.jermaine.awesomelibraries.datamodel.repo.Repo

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @get:GET("/users/jermainedilao/repos")
    val repos: Call<List<Repo>>
}