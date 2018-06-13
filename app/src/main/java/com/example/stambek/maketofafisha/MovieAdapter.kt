package com.example.stambek.maketofafisha

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.cell_movie.view.*


class MovieAdapter(var data: ArrayList<String>, var data2: ArrayList<String>,var data3: ArrayList<String>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return  MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_movie, null))

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.primary.text = data[position]
        holder.secondary.text = data2[position]
        holder.third.text = data3[position]
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary2
        var secondary = view.secondary2
        var third = view.third
    }



}
