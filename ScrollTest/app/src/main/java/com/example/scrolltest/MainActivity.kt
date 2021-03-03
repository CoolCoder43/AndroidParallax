package com.example.scrolltest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
   lateinit var linearlayout:ConstraintLayout
    lateinit var recyclerview :RecyclerView
    lateinit var button: Button
    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    private var height_layout:Int = 0
    lateinit  var model: ViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearlayout = findViewById<ConstraintLayout>(R.id.linearLayout)
        recyclerview = findViewById(R.id.recyclerView)
        button = findViewById(R.id.button)
        moviesAdapter = MoviesAdapter(movieList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = layoutManager
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = moviesAdapter
        prepareMovieData()
        model = ViewModel()

        val params: ViewGroup.LayoutParams = linearlayout.layoutParams
        this.height_layout = params.height
        val  parms = button.layoutParams as ConstraintLayout.LayoutParams
        model.storeGrCoValForButton(30.0f, this.height_layout.toFloat(), 550.0f, 120.0f)

        recyclerview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val sc_h = recyclerview.computeVerticalScrollOffset()
                params.height = height_layout - sc_h
            if (params.height <= 120){
                params.height = 120
            }
                linearlayout.layoutParams = params
                parms.marginStart = model.calculateCurrentButtonPosition(params.height.toFloat()).toInt()
                button.requestLayout()
                Log.d("TAG", "onCreate: " + params.height)

//            if (params.height < 80){
//                params.height = 80
//                linearlayout.layoutParams = params
//            }
            //Log.d("TAG", "onCreate: " + sc_h)
        }
        moviesAdapter.onItemClick = {  ->
            val intent = Intent(this, YoutubeActivity::class.java)
            startActivity(intent)            // do something with your item
        }



    }




    private fun prepareMovieData() {
        var movie = MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015")
        movieList.add(movie)
        movie = MovieModel("Inside Out", "Animation, Kids & Family", "2015")
        movieList.add(movie)
        movie = MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Shaun the Sheep", "Animation", "2015")
        movieList.add(movie)
        movie = MovieModel("The Martian", "Science Fiction & Fantasy", "2015")
        movieList.add(movie)
        movie = MovieModel("Mission: Impossible Rogue Nation", "Action", "2015")
        movieList.add(movie)
        movie = MovieModel("Up", "Animation", "2009")
        movieList.add(movie)
        movie = MovieModel("Star Trek", "Science Fiction", "2009")
        movieList.add(movie)
        movie = MovieModel("The LEGO MovieModel", "Animation", "2014")
        movieList.add(movie)
        movie = MovieModel("Iron Man", "Action & Adventure", "2008")
        movieList.add(movie)
        movie = MovieModel("Aliens", "Science Fiction", "1986")
        movieList.add(movie)
        movie = MovieModel("Chicken Run", "Animation", "2000")
        movieList.add(movie)
        movie = MovieModel("Back to the Future", "Science Fiction", "1985")
        movieList.add(movie)
        movie = MovieModel("Raiders of the Lost Ark", "Action & Adventure", "1981")
        movieList.add(movie)
        movie = MovieModel("Goldfinger", "Action & Adventure", "1965")
        movieList.add(movie)
        movie = MovieModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
        movieList.add(movie)
        moviesAdapter.notifyDataSetChanged()
    }
}

