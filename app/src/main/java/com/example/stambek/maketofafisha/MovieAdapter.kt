package com.example.stambek.maketofafisha

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stambek.maketofafisha.ModelOfApiMovie.Movie
import com.example.stambek.maketofafisha.model.GenData


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
            while(movie.result[i].name != data.data1[position]){
                i++
            }
            Information.actors = movie.result[i].actors
            Information.rejisser = movie.result[i].rejisser
            Information.country = movie.result[i].countries
            Information.image = movie.result[i].image
            Information.votes = movie.result[i].vote
            Information.premier = movie.result[i].premier_ua

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
