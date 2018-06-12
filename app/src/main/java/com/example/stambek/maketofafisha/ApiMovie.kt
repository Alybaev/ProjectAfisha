package com.example.stambek.maketofafisha

import com.example.stambek.maketofafisha.ModelOfApiCinema.Cinema
import com.example.stambek.maketofafisha.ModelOfApiMovie.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiMovie {

    companion object {
        final val BASE_URL = "https://kinoafisha.ua/ajax/"
    }

    @GET("kinoafisha_load/")
    fun getData(): Call<Movie>

}