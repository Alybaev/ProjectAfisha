package com.example.stambek.maketofafisha.utils

import com.example.stambek.maketofafisha.model.ModelOfApiMovie.Movie
import retrofit2.Call
import retrofit2.http.GET

interface FormServices {
    @GET("kinoafisha_load/")
    fun getData(): Call<Movie>
}