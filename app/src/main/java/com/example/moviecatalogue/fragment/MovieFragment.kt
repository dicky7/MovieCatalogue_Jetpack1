package com.example.moviecatalogue.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.DataMovie
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.model.MovieCallback
import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.viewModel.MovieViewModel

class MovieFragment : Fragment(),MovieCallback {
    lateinit var movieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieBinding = FragmentMovieBinding.inflate(inflater,container,false)
        return movieBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val movieModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movie = movieModel.getMovieModel()
            val adapter = MovieAdapter(this)
            adapter.setMovie(movie)
            with(movieBinding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(movieModel: MovieModel) {
        if (activity != null){
            val minetype = "text/plain"
                ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(minetype)
                    .setChooserTitle("Share This Movie Now")
                    .setText(resources.getString(R.string.share_text))
                    .startChooser()
        }
    }

}