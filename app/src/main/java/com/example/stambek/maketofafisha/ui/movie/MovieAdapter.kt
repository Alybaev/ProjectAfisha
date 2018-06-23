package com.example.stambek.maketofafisha.ui.movie

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stambek.maketofafisha.R
import com.example.stambek.maketofafisha.ui.ThirdActivity
import com.example.stambek.maketofafisha.model.ModelOfApiMovie.Movie
import com.example.stambek.maketofafisha.model.GenData
import com.example.stambek.maketofafisha.model.InformationMovie
import com.example.stambek.maketofafisha.utils.Constants.Companion.MOVIE_INFO_KEY


import kotlinx.android.synthetic.main.cell_movie.view.*


class MovieAdapter(var context : Context, var data: GenData, var movie : Movie) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return  MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_movie, null))
    }

    override fun getItemCount(): Int {
        return data.data1.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.primary.text = data.data1[position]
        holder.secondary.text = data.data3[position]
        holder.third.text = data.data2[position]
        holder.layout.setOnClickListener {
            val intent = Intent(context, ThirdActivity::class.java)
            intent.putExtra("nameOfMovie", data.data1[position])
            var i = 0
            while(movie.result[i].name != data.data1[position]) {
                i++
            }

            var actors = movie.result[i].actors
            var rejisser = movie.result[i].rejisser
            var country = movie.result[i].countries
            var image = movie.result[i].image
            var votes = movie.result[i].vote
            var premier = movie.result[i].premier_ua
            var infoMovie = InformationMovie(rejisser,actors,country,premier,votes,image)
            intent.putExtra(MOVIE_INFO_KEY,infoMovie)
            context.startActivity(intent)
        }
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary2
        var secondary = view.secondary2
        var third = view.third
        var layout = view.movie_layout
    }

    fun setMData(data: GenData, movie: Movie) {
        this.movie = movie
        this.data = data
        notifyDataSetChanged()
    }



}
