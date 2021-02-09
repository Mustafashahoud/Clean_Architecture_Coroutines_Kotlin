package com.android.mustafa.dynamicfeatures.movies.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.mustafa.cleanarchapp.CleanArchApp
import com.android.mustafa.cleanarchapp.R
import com.android.mustafa.core.di.AppViewModelFactory
import com.android.mustafa.core.di.DaggerCoreComponent
import com.android.mustafa.dynamicfeatures.movies.di.DaggerMoviesComponent
import com.android.mustafa.dynamicfeatures.movies.di.MoviesComponent
import javax.inject.Inject

class MoviesFragment: Fragment(R.layout.fragment_movies) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.postPage(1)
        subscribeResults()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMoviesComponent
            .builder()
            .coreComponent(CleanArchApp.coreComponent(requireContext()))
            .build()
            .inject(this)

    }

    private fun subscribeResults() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            if (it.data != null)
            Log.d("MoviesFragment", it.data.toString())
        }
    }
}