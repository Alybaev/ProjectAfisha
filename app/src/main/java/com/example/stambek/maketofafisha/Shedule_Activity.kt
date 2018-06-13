package com.example.stambek.maketofafisha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.stambek.maketofafisha.ModelOfApiCinema.Cinema
import kotlinx.android.synthetic.main.activity_shedule_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.stambek.maketofafisha.utils.Constants.Companion.BASE_URL
import com.example.stambek.maketofafisha.utils.NetWork


class Shedule_Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shedule_)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val apiCinema = NetWork.retrofit.create(ApiCinema::class.java)
        val call = apiCinema.getData()

        call.enqueue(object : Callback<Cinema>{

            override fun onResponse(call: Call<Cinema>?, response: Response<Cinema>?) {
                val cinemas = response!!.body()
                var nameOfCinema =  ArrayList<String>()
                var idOfCinema = ArrayList<String>()
                var addressOfCinema =  ArrayList<String>()

                var i = 0
                while(i< cinemas?.result?.unmain?.size!!){
                    idOfCinema.add(cinemas?.result?.unmain!![i]?.id!!)
                    nameOfCinema.add(cinemas?.result?.unmain[i]?.name!!)
                    addressOfCinema.add(cinemas?.result?.unmain[i]?.address!!)
                    i++
                }
                list_view.layoutManager= LinearLayoutManager(this@Shedule_Activity)
                list_view.adapter = CinemaAdapter(this@Shedule_Activity,nameOfCinema,addressOfCinema,idOfCinema)


            }

            override fun onFailure(call: Call<Cinema>?, t: Throwable?) {
                Toast.makeText(this@Shedule_Activity,t?.message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}
