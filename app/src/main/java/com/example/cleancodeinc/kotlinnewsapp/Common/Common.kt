package com.example.cleancodeinc.kotlinnewsapp.Common

import com.example.cleancodeinc.kotlinnewsapp.Interface.NewService
import com.example.cleancodeinc.kotlinnewsapp.Remote.RetrofitClient
import java.lang.StringBuilder
import javax.xml.transform.Source

object Common {

    val BASE_URL = "https://newsapi.org"
    val API_KEY = "2cda9e9005464b6987b4799fe7311336"


    val newService: NewService
        get() = RetrofitClient.getClient(BASE_URL).create(NewService::class.java)


    fun getNewsAPI(source: String): String {
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?sources=")
                .append(source)
                .append("&apiKey=")
                .append(API_KEY)
                .toString()

        return apiUrl
    }


}