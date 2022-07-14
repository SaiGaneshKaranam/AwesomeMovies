package com.example.awesomemovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast.makeText as makeText1

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var noitem=findViewById<TextView>(R.id.noitem)
        var movieListView = findViewById<RecyclerView>(R.id.movieList)
        var progress = findViewById<ProgressBar>(R.id.progressbar)
        movieListView.layoutManager = LinearLayoutManager(this)
        val movieListAdapter = MovieListAdapter()
        lateinit var  movieList: List<MovieItem>
        movieListView.adapter = movieListAdapter
        var searchQueryText = findViewById<AppCompatEditText>(R.id.searchQuery)
        val movieApi = MovieApiClient.getRetrofit().create(MovieApi::class.java)
        var searchButton = findViewById<AppCompatButton>(R.id.searchButton)
        searchButton.setOnClickListener {


            noitem.visibility=View.GONE
            val searchQuery = searchQueryText.text.toString()
            if (searchQuery.isNullOrBlank()) {
                makeText1(
                    this,
                    "search cannot be empty. Minimum 3 characters are required.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if (searchQuery.length < 3) {
                makeText1(
                    this,
                    "Minimum 3 characters are required.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                movieApi.searchMovies(searchQuery, 1).enqueue(object : Callback<SearchResult> {
                    override fun onResponse(
                        call: Call<SearchResult>,
                        response: Response<SearchResult>
                    ) {
                        response.body()?.let {

                            movieList= it.search
                            if(movieList==null) {
                                movieListView.visibility = View.GONE
                                progress.visibility=View.GONE
                                noitem.visibility=View.VISIBLE

                            }
                            else{
                                movieListView.visibility=View.VISIBLE
                            movieListAdapter.setData(movieList,progress)

                            }


                        }
                    }
                    

                    override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                    }


                })
                progress.visibility= View.VISIBLE
            }



        }


       movieListAdapter.setOnItemClickListener(object:MovieListAdapter.onItemClickListener{


           override fun onItemClick(position: Int) {
               val intent:Intent = Intent(this@MainActivity,movie_Details::class.java)
                intent.putExtra("id", movieList.get(position).imdbID);
               startActivity(intent)

           }

       })



    }

}
