package com.example.stambek.maketofafisha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.stambek.maketofafisha.Model.Feed
import kotlinx.android.synthetic.main.activity_shedule_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Shedule_Activity : AppCompatActivity() {

    companion object {
        private val BASE_URL = "https://kinoafisha.ua/ajax/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shedule_)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val apiCinema = retrofit.create(ApiCinema::class.java)
        val call = apiCinema.getData()

        call.enqueue(object : Callback<Feed>{

            override fun onResponse(call: Call<Feed>?, response: Response<Feed>?) {
                val cinemas = response!!.body()
                val nameOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)
                val idOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)
                val addressOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)

                var i = 0
                while(i<cinemas?.result?.unmain?.size){
                    nameOfCinema[i] = cinemas?.result?.unmain[i]?.name!!
                    i++
                }
                list_view.adapter = ( ArrayAdapter<String>(
                        this@Shedule_Activity,
                        android.R.layout.simple_list_item_1,
                        nameOfCinema
                ))

            }

            override fun onFailure(call: Call<Feed>?, t: Throwable?) {
                Toast.makeText(this@Shedule_Activity,t?.message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}
