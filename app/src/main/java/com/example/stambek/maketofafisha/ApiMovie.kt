package com.example.stambek.maketofafisha

import com.example.stambek.maketofafisha.ModelOfApiMovie.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiMovie {

    @GET("kinoafisha_load/")
    fun getData(): Call<Movie>

}