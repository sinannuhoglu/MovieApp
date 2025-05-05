package com.sinannuhoglu.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinannuhoglu.movieapp.databinding.ItemImageTitleRecyclerViewBinding
import com.sinannuhoglu.movieapp.model.MovieItem
import com.sinannuhoglu.movieapp.util.loadRoundedImage

interface UpcomingMovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class UpcomingMoviesAdapter(private val movieList: List<MovieItem?>?, private val movieClickListener: UpcomingMovieClickListener):
    RecyclerView.Adapter<UpcomingMoviesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemImageTitleRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesAdapter.ViewHolder {
        return ViewHolder(ItemImageTitleRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList!![position]

        holder.binding.textViewUpComing.text = movie?.title
        holder.binding.imageViewUpcomingMovie.loadRoundedImage(movie?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = movie?.id)
        }
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }
}