package com.sinannuhoglu.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinannuhoglu.movieapp.databinding.ItemImageTitleOverviewRecyclerViewBinding
import com.sinannuhoglu.movieapp.model.MovieItem
import com.sinannuhoglu.movieapp.util.GenreMapper
import com.sinannuhoglu.movieapp.util.loadRoundedImage

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class NowPlayingMoviesAdapter (private val movieList: List<MovieItem?>, private val movieClickListener: MovieClickListener):
    RecyclerView.Adapter<NowPlayingMoviesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemImageTitleOverviewRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageTitleOverviewRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val genreText = GenreMapper.mapGenres(movie?.genreİds)

        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.textViewMovieVote.text = String.format("%.1f", movie?.voteAverage ?: 0.0)
        holder.binding.textViewGenre.text = genreText

        holder.binding.imageViewMovie.loadRoundedImage(movie?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = movie?.id)
        }
    }
}