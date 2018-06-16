package com.example.stambek.maketofafisha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient

import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.toolbar.*

class ThirdActivity : AppCompatActivity() {

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
        Information.actors = convertionHtml(Information.actors)
        Information.rejisser = convertionHtml(Information.rejisser)
        information.text = getString(R.string.information,Information.rejisser,Information.actors,Information.country,Information.premier,Information.votes)
        var img_url = "${"https://kinoafisha.ua"+Information.image}"
        imageView.webViewClient = Callback()
        val webSettings = imageView.settings
        webSettings.builtInZoomControls = true
        imageView.loadUrl(img_url)

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
