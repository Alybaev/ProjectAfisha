package com.example.stambek.maketofafisha.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.stambek.maketofafisha.R
import com.example.stambek.maketofafisha.model.InformationMovie
import com.example.stambek.maketofafisha.utils.Constants.Companion.MOVIE_INFO_KEY

import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.toolbar.*

class ThirdActivity : AppCompatActivity() {
    var infoMovie:InformationMovie?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()
    }
    fun init(){
        initToolbar()
        initReceivedData()
    }
    fun initReceivedData(){
        toolbar.title = intent.getStringExtra("nameOfMovie")
        infoMovie = intent.getSerializableExtra(MOVIE_INFO_KEY) as InformationMovie

        var actors = convertionHtml(infoMovie!!.actors)
        var rejisser = convertionHtml(infoMovie!!.rejisser)

        information.text = getString(R.string.information, rejisser, actors, infoMovie!!.country, infoMovie!!.premier, infoMovie!!.votes)

        var img_url = "${"https://kinoafisha.ua"+ infoMovie!!.image}"

        img_url = img_url.replace("sm_", "")
        Glide.with(this)
                .load(img_url)
                .into(imageView)

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

    private fun convertionHtml(actors: String): String {
        var actorsArr = actors.toCharArray()
        var result = ""
        var i = 1

        while (i < actors.length){
            if(actorsArr[i - 1] == '>'){
                while(actorsArr[i] != '<'){
                    result += actorsArr[i]
                    i++
                }
            }
            i++
        }

        return result
    }


    private class Callback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return false
        }
    }
}
