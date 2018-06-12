package com.example.stambek.maketofafisha

import com.example.stambek.maketofafisha.ModelOfApiCinema.Cinema
import retrofit2.Call
import retrofit2.http.GET

interface ApiCinema {

    companion object {
        final val BASE_URL = "https://kinoafisha.ua/ajax/"
    }

    @GET("kinoteatrs_load/")
    fun getData(): Call<Cinema>

}