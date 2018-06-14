package com.example.stambek.maketofafisha

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stambek.maketofafisha.model.GenData

import kotlinx.android.synthetic.main.cell_movie.view.*


class MovieAdapter(var data: GenData) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
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
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary2
        var secondary = view.secondary2
        var third = view.third
    }

    fun setMData(data: GenData) {
        this.data = data
        notifyDataSetChanged()
    }



}
