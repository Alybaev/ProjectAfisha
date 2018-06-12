package com.example.stambek.maketofafisha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    companion object {
        private val BASE_URL = "https://kinoafisha.ua/ajax/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val idOfCinema = intent.getStringExtra("idOfCinema")
        Toast.makeText(this,idOfCinema,Toast.LENGTH_LONG).show()



    }
}
