package com.example.stambek.maketofafisha

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.stambek.maketofafisha.ModelOfApiCinema.Cinema
import kotlinx.android.synthetic.main.activity_shedule_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.AdapterView



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

        call.enqueue(object : Callback<Cinema>{

            override fun onResponse(call: Call<Cinema>?, response: Response<Cinema>?) {
                val cinemas = response!!.body()
                val nameOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)
                val idOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)
                val addressOfCinema = arrayOfNulls<String>(cinemas?.result?.unmain?.size!!)

                var i = 0
                while(i<cinemas?.result?.unmain?.size){
                    nameOfCinema[i] = cinemas?.result?.unmain[i]?.name!!
                    addressOfCinema[i] = cinemas?.result?.unmain[i]?.address!!
                    i++
                }
                list_view.adapter = ( ArrayAdapter<String>(
                        this@Shedule_Activity,
                        android.R.layout.simple_list_item_1,
                        nameOfCinema
                ))
                list_view.onItemClickListener = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                        intent = Intent(applicationContext, SecondActivity::class.java)
                        intent.putExtra("idOfCinema", cinemas?.result?.unmain[position].id.toString())
                        startActivity(intent)

                    }
                }


            }

            override fun onFailure(call: Call<Cinema>?, t: Throwable?) {
                Toast.makeText(this@Shedule_Activity,t?.message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}
