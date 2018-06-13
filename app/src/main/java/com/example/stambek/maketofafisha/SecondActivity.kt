package com.example.stambek.maketofafisha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import com.example.stambek.maketofafisha.ModelOfApiMovie.Movie
import com.example.stambek.maketofafisha.utils.Constants
import com.example.stambek.maketofafisha.utils.Constants.Companion.BASE_URL
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response


class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val idOfCinema = intent.getStringExtra("idOfCinema")

        val retrofit2 = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val apiMovie = retrofit2.create((ApiMovie::class.java))
        val call2 = apiMovie.getData()

        call2.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                val movies = response!!.body()

                val nameOfMovies = ArrayList<String>()
                val timeOfMovies = ArrayList<String>()
                val hallOfMovies = ArrayList<String>()

                var i = 0
                while(i < movies?.result!!.size){
                    var j = 0
                    while(j < movies!!.result[i]!!.sessions.size){
                        if (movies!!.result[i]!!.sessions[j].k_id ==idOfCinema || movies!!.result[i]!!.sessions[j].k_id == null){
                            nameOfMovies.add(movies?.result[i].name)

                            var changeStr = movies!!.result[i]!!.sessions[j].sessions
                            var changeStrAr = changeStr.toCharArray()
                            if(changeStrAr[changeStr.length - 10] != 'p')
                                changeStr = changeStr.substring(changeStr.length - 15, changeStr.length - 10)
                            else{
                                changeStr = changeStr.substring(changeStr.length - 18, changeStr.length - 13)
                            }
                            timeOfMovies.add(changeStr)

                            hallOfMovies.add(movies!!.result[i]!!.sessions[j].h_name)
                        }
                       j++
                    }
                    i++
                }
                list_view2.layoutManager = LinearLayoutManager(this@SecondActivity)
               list_view2.adapter = MovieAdapter(nameOfMovies,timeOfMovies,hallOfMovies)
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Toast.makeText(this@SecondActivity,t?.message, Toast.LENGTH_SHORT).show()
            }

        })



    }
}
