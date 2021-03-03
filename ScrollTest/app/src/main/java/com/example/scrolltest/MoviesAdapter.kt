package com.example.scrolltest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
internal class MoviesAdapter(private var moviesList: List<MovieModel>) :

    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var year: TextView = view.findViewById(R.id.year)
        var genre: TextView = view.findViewById(R.id.genre)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        itemView.setOnClickListener {
            onItemClick?.invoke()
        }
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        if (position == 0 || position == 1){
            holder.title.text = ""
            holder.genre.text = ""
            holder.year.text = ""
            holder.itemView.background = null
        }else{
            holder.title.text = movie.getTitle()
            holder.genre.text = movie.getGenre()
            holder.year.text = movie.getYear()
            holder.itemView.background = android.graphics.Color.BLACK.toDrawable()
        }


    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
    var onItemClick: (() -> Unit)? = null

}