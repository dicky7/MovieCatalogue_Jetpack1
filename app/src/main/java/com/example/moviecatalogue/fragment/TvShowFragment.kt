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
import com.example.moviecatalogue.adapter.TvShowAdapter
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.model.MovieCallback
import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.model.TvCallback
import com.example.moviecatalogue.viewModel.TvViewModel
import kotlinx.android.synthetic.main.list_item.*

class TvShowFragment: Fragment(),TvCallback{
    lateinit var tvBinding: FragmentTvShowBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvBinding = FragmentTvShowBinding.inflate(inflater,container,false)
        return tvBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity !=null){
            val tvModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
            val tvShow = tvModel.getTvModel()
            val adapter = TvShowAdapter(this)
            adapter.setTvShow(tvShow)

            with(tvBinding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(tvModel: MovieModel) {
        if (activity != null){
            val minetype = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(minetype)
                .setChooserTitle("Share This TvShow Now")
                .setText(resources.getString(R.string.share_text))
                .startChooser()
        }
    }


}