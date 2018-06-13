package com.example.stambek.maketofafisha

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_shedule_.view.*
import kotlinx.android.synthetic.main.cell_cinema.view.*

class CinemaAdapter(var context:Context, var data: ArrayList<String>, var data2: ArrayList<String>, var data3 :ArrayList<String>) : RecyclerView.Adapter<CinemaAdapter.MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
       return  MViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_cinema, null,false))

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.primary.text = data[position]
        holder.secondary.text = data2[position]

        holder.layout.setOnClickListener (object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("idOfCinema", data3[position])
                context.startActivity(intent)
            }

        })

    }

    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var primary = view.primary
        var secondary = view.secondary
        var layout = view.cinema_layout2
    }





}
