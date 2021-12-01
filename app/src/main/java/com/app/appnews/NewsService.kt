package com.app.appnews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
//https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//https://newsapi.org/v2/everything?q=apple&from=2021-10-06&to=2021-10-06&sortBy=popularity&apiKey=API_KEY
//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=757fbfbbfa2f422ca1ca13d0bdaff4ad

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "757fbfbbfa2f422ca1ca13d0bdaff4ad"
interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String, @Query("page")page: Int, @Query("category")category: String): Call<News>
}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}