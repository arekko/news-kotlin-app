package com.example.cleancodeinc.kotlinnewsapp.Interface

import com.example.cleancodeinc.kotlinnewsapp.Model.WebSite
import retrofit2.Call
import retrofit2.http.GET

interface NewService {

    @get:GET("v2/sources?apiKey=2cda9e9005464b6987b4799fe7311336")
    val sources: Call<WebSite>
}