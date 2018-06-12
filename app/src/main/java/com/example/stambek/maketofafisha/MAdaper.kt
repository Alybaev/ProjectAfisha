package com.example.stambek.maketofafisha

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_cinema.view.*

class MAdaper(var data: ArrayList<String>, var data2: ArrayList<String>) : RecyclerView.Adapter<MAdaper.MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
       return  MViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_cinema, null, false))

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.primary.text = data[position]
        holder.secondary.text = data2[position]

    }

    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary
        var secondary = view.secondary
    }


}