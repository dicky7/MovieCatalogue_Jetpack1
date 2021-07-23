package com.example.moviecatalogue.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.DataMovie
import com.example.moviecatalogue.model.MovieModel

class DetailViewModel: ViewModel() {
    companion object{
        const val TV_SHOW_DETAIL = "tvShowDetail"
        const val MOVIE_DETAIL = "movieDetail"
    }
    private lateinit var movieModel : MovieModel

    fun setMovieDetail(movieId: String, detailId: String){
        when(detailId){
            TV_SHOW_DETAIL->{
                for (tvData in DataMovie.generateDataTv()){
                    if (tvData.id == movieId) movieModel = tvData
                }
            }
            MOVIE_DETAIL->{
                for (movieData in DataMovie.generateDataMovie()){
                    if (movieData.id == movieId) movieModel = movieData
                }
            }
        }
    }

    fun getMovieDetail() = movieModel
}