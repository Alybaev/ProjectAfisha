package com.example.stambek.maketofafisha
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import com.example.stambek.maketofafisha.ModelOfApiMovie.Movie
import com.example.stambek.maketofafisha.model.GenData
import com.example.stambek.maketofafisha.utils.NetWork
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondActivity : AppCompatActivity() {
    var call2: Call<Movie>? = null
    var data: GenData? = null
    var movies: Movie? = null
    var mAdapter: MovieAdapter? = null
    var idOfCinema : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()
        getDataFromBack()
    }
    private fun init() {
        initReceivedData()
        initToolbar()
        val apiMovie = NetWork.retrofit.create(ApiMovie::class.java)
        call2 = apiMovie.getData()
        data = GenData(ArrayList(), ArrayList(),ArrayList())
        movies = Movie(ArrayList())
        mAdapter = MovieAdapter(this@SecondActivity,data!!,movies!!)
        list_view2.layoutManager = LinearLayoutManager(this@SecondActivity)
        list_view2.adapter = mAdapter
    }
    private fun initReceivedData(){
        toolbar.title = intent.getStringExtra("nameOfCinema")
        idOfCinema = intent.getStringExtra("idOfCinema")
    }
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
    fun getDataFromBack(){
        call2!!.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                movies = response!!.body()
                for(i in 0 until movies?.result!!.size){
                    for(j in 0 until  movies!!.result[i].sessions.size){
                        if (movies!!.result[i].sessions[j].k_id == idOfCinema || idOfCinema == null){
                            data!!.data1.add(movies!!.result[i].name)!!
                            data!!.data2.add(movies!!.result[i].sessions[j].h_name)!!
                            data!!.data3.add(pullOutTime(movies!!.result[i].sessions[j].sessions))
                        }
                    }

                }

                mAdapter!!.setMData(data!!,movies!!)
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Toast.makeText(this@SecondActivity,t?.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun pullOutTime(changeStr : String) : String {
        var changeStrAr = changeStr.toCharArray()
        var result = changeStr
        if(changeStrAr[changeStr.length - 10] != 'p') {
            result = changeStr.substring(changeStr.length - 15, changeStr.length - 10)
        }
        else{
            result = changeStr.substring(changeStr.length - 18, changeStr.length - 13)
        }
        return result
    }

}
