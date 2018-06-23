package com.example.stambek.maketofafisha.utils

import com.example.stambek.maketofafisha.model.ModelOfApiCinema.Cinema
import retrofit2.Call
import retrofit2.http.GET

interface ApiCinema {
    @GET("kinoteatrs_load/")
    fun getData(): Call<Cinema>

}