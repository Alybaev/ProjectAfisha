package com.example.stambek.maketofafisha

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.cell_cinema.view.*


class MovieAdapter(var data: ArrayList<String>, var data2: ArrayList<String>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return  MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_cinema, null))

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.primary.text = data[position]
        holder.secondary.text = data2[position]
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary
        var secondary = view.secondary
    }



}
