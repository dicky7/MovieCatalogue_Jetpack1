package com.example.moviecatalogue.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ListItemBinding
import com.example.moviecatalogue.model.MovieCallback
import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.model.TvCallback
import com.example.moviecatalogue.viewModel.DetailViewModel

class TvShowAdapter(private val callback: TvCallback): RecyclerView.Adapter<TvShowAdapter.TvViewHolder>(){
    private var listTv = ArrayList<MovieModel>()
    fun setTvShow(tvShow : ArrayList<MovieModel>?){
        if (tvShow == null) return
        this.listTv.clear()
        this.listTv.addAll(tvShow)
    }

    inner class TvViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvModel: MovieModel){
            with(binding){
                listItemTitle.text = tvModel.title
                listItemRelease.text = tvModel.realese
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, tvModel.id)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, DetailViewModel.TV_SHOW_DETAIL)
                    itemView.context.startActivity(intent)
                }
                shareButton.setOnClickListener { callback.onShareClick(tvModel) }
                Glide.with(itemView.context)
                    .load(tvModel.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(listItemImg)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TvViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size

}