package com.example.moviecatalogue.adapter

import android.content.ComponentCallbacks
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ListItemBinding
import com.example.moviecatalogue.model.MovieCallback

import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.model.TvCallback
import com.example.moviecatalogue.viewModel.DetailViewModel.Companion.MOVIE_DETAIL

class MovieAdapter(private val callback: MovieCallback): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MovieModel>()
    fun setMovie(movie: ArrayList<MovieModel>?){
        if (movie == null)return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    inner class MovieViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieModel: MovieModel){
            with(binding) {
                listItemTitle.text = movieModel.title
                listItemRelease.text = movieModel.realese
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movieModel.id)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, MOVIE_DETAIL)
                    itemView.context.startActivity(intent)
                }
                shareButton.setOnClickListener { callback.onShareClick(movieModel) }
                Glide.with(itemView.context)
                    .load(movieModel.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(listItemImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie =listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size
}