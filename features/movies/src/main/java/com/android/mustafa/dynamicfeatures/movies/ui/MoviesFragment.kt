package com.android.mustafa.dynamicfeatures.movies.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.mustafa.cleanarchapp.CleanArchApp
import com.android.mustafa.commons.ui.BaseFragment
import com.android.mustafa.commons.ui.adapter.InfinitePager
import com.android.mustafa.commons.ui.bindings.hide
import com.android.mustafa.commons.ui.bindings.show
import com.android.mustafa.domain.commons.ErrorEntity
import com.android.mustafa.domain.commons.ErrorMessageUtil
import com.android.mustafa.dynamicfeatures.movies.databinding.FragmentMoviesBinding
import com.android.mustafa.dynamicfeatures.movies.di.DaggerMoviesComponent
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    lateinit var rvAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        initRv()
        subscribeResults()
    }

    private fun initRv() {
        rvAdapter = MoviesAdapter()
        binding.rvMovies.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addOnScrollListener(object : InfinitePager(rvAdapter) {
                override fun loadMoreCondition(): Boolean {
                    return true
                }

                override fun loadMore() {
                    viewModel.loadMore()
                }
            })
            setHasFixedSize(true)
        }

    }

    private fun subscribeResults() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    hideError()
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    hideError()
                    binding.progressBar.hide()
                    if (it.data != null) {
                        rvAdapter.submitList(it.data)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.rvMovies.hide()
                    showError(it)
                }
            }

        }
    }

    private fun showError(resource: Resource.Error) {
        binding.errorMessageTitleTextView.show()
        binding.errorMessageTextView.show()

        when (resource.error) {
            is ErrorEntity.NetworkError -> {
                binding.retryTextView.show()
            }
            is ErrorEntity.ServerError -> {
                binding.goBackButton.show()
            }
            is ErrorEntity.UnknownError -> {
                binding.goBackButton.show()
            }
        }
        binding.errorMessageTextView.text =
            ErrorMessageUtil.getErrorMessage(requireContext(), resource.error)

    }

    private fun hideError() {
        binding.errorMessageTitleTextView.hide()
        binding.errorMessageTextView.hide()
        binding.retryTextView.hide()
        binding.goBackButton.hide()
    }


    override fun onInitDependencyInjection() {
        DaggerMoviesComponent
            .builder()
            .coreComponent(CleanArchApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    private fun setListeners() {
        binding.retryTextView.setOnClickListener {
            viewModel.retry()
        }
        binding.goBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}