package com.example.moviecatalogue.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.DataMovie

class TvViewModel: ViewModel() {
    fun getTvModel() = DataMovie.generateDataTv()
}