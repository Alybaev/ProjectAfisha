package com.example.stambek.maketofafisha

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stambek.maketofafisha.model.GenData
import kotlinx.android.synthetic.main.cell_cinema.view.*

class CinemaAdapter(var context: Context, var data: GenData) : RecyclerView.Adapter<CinemaAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        return MViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_cinema, null, false))
    }

    override fun getItemCount(): Int {
        return data.data1.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.primary.text = data.data1[position]
        holder.secondary.text = data.data2[position]

        holder.layout.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("idOfCinema", data.data3[position])
            intent.putExtra("nameOfCinema", data.data1[position])
            context.startActivity(intent)
        }

    }

    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var primary = view.primary
        var secondary = view.secondary
        var layout = view.cinema_layout2
    }

    fun setMData(data: GenData) {
        this.data = data
        notifyDataSetChanged()
    }


}
