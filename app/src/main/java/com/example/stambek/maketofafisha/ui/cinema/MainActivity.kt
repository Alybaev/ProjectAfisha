package com.example.stambek.maketofafisha.ui.cinema
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.stambek.maketofafisha.utils.ApiCinema
import com.example.stambek.maketofafisha.R
import com.example.stambek.maketofafisha.model.ModelOfApiCinema.Cinema
import com.example.stambek.maketofafisha.model.GenData
import kotlinx.android.synthetic.main.activity_shedule_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.stambek.maketofafisha.utils.NetWork


class MainActivity : AppCompatActivity() {

    var data: GenData? = null
    var mAdapter: CinemaAdapter? = null
    var call: Call<Cinema>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shedule_)
        title = "Кинотеатры"


        setSupportActionBar(findViewById(R.id.toolbar))
        init()
        getDataFromBack()
    }



    private fun getDataFromBack() {
        call!!.enqueue(object : Callback<Cinema> {
            override fun onResponse(call: Call<Cinema>?, response: Response<Cinema>?) {
                val cinemas = response!!.body()
                for (i in 0 until cinemas?.result?.unmain?.size!!) {
                    data!!.data1.add(cinemas.result.unmain[i].name)
                    data!!.data2.add(cinemas.result.unmain[i].address)
                    data!!.data3.add(cinemas.result.unmain[i].id)
                }
                mAdapter!!.setMData(data!!)
            }

            override fun onFailure(call: Call<Cinema>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        val apiCinema = NetWork.retrofit.create(ApiCinema::class.java)
        call = apiCinema.getData()
        data = GenData(ArrayList(), ArrayList(), ArrayList())
        mAdapter = CinemaAdapter(this@MainActivity, data!!)
        list_view.layoutManager = LinearLayoutManager(this@MainActivity)
        list_view.adapter = mAdapter

    }
}
