package com.example.moviecatalogue.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.DataMovie

class MovieViewModel: ViewModel() {
    fun getMovieModel() = DataMovie.generateDataMovie()
}