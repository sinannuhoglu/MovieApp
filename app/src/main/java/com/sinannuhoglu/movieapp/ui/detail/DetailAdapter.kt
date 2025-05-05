package com.sinannuhoglu.movieapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinannuhoglu.movieapp.databinding.ItemImageTitleRecyclerViewBinding
import com.sinannuhoglu.movieapp.model.Cast
import com.sinannuhoglu.movieapp.util.loadCircleImage

interface DetailAdapterClickListener {
    fun onMovieClicked(cast: Cast?)
}

class DetailAdapter(private val movieList: List<Cast?>?, private val movieClickListener: DetailAdapterClickListener):
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemImageTitleRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageTitleRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList!![position]

        holder.binding.textViewUpComing.text = movie?.name
        holder.binding.imageViewUpcomingMovie.loadCircleImage(movie?.profilePath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }
}