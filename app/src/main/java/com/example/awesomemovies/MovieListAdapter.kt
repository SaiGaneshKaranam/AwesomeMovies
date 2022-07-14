package com.example.awesomemovies

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.Files.find


class MovieListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val moviesList = mutableListOf<MovieItem>()
    private lateinit var mListener :onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return MovieItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false),mListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieItemViewHolder) {
            holder.bind(moviesList[position])
            /*holder.itemView.setOnClickListener(View.OnClickListener {


                val imdbid =holder.id





                val movieApi = MovieApiClient.getRetrofit().create(MovieApi::class.java)
                movieApi.getData(imdbid).enqueue(object : Callback<MovieInDetail> {
                    override fun onResponse(
                        call: Call<MovieInDetail>,
                        response: Response<MovieInDetail>
                    ) {
                        response.body()?.let {

                            val moviedetails: MovieInDetail = it


                            holder.movieYear.text=moviedetails.imdbID.toString()


                        }
                    }




                    override fun onFailure(call: Call<MovieInDetail>, t: Throwable) {

                    }


                })



            })*/
            /*holder.itemView.setOnClickListener {
                mListener.onItemClick(holder.id.toString())
            }*/




        }



    }




    override fun getItemCount(): Int {
        return moviesList.size

    }


    fun setData(movieList: List<MovieItem>,progress : ProgressBar) {
        this.moviesList.clear()
        this.moviesList.addAll(movieList)
        notifyDataSetChanged()
        progress.visibility = View.GONE
    }


}


class MovieItemViewHolder(itemView: View , listener:MovieListAdapter.onItemClickListener) : RecyclerView.ViewHolder(itemView) {
    var movieThumbnail: ImageView

    lateinit var id : String
    var movieTitle: TextView
    var movieYear: TextView
    var type: TextView


    init {
        movieThumbnail = itemView.findViewById(R.id.thumbnail)
        movieTitle = itemView.findViewById(R.id.movieTitle)
        movieYear = itemView.findViewById(R.id.movieReleaseYear)
        type = itemView.findViewById(R.id.type)
        itemView.setOnClickListener{
             listener.onItemClick(adapterPosition)
         }



    }

    fun bind(movieItem: MovieItem) {
        Glide.with(movieThumbnail).load(movieItem.poster).into(movieThumbnail)
        movieTitle.text = movieItem.title
        movieYear.text = movieItem.year
        type.text = movieItem.type
        id=movieItem.imdbID


    }


}
