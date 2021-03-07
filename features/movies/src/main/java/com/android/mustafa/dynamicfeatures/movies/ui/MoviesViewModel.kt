package com.android.mustafa.dynamicfeatures.movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.android.mustafa.commons.ui.ViewModelBase
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.domain.movies.usecase.GetMovies
import com.android.mustafa.domain.commons.EitherResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModelBase(Dispatchers.IO) {


    private var pageNumber = 1
    private val moviePageLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        moviePageLiveData.value = 1
    }


    private val _movieListLiveData = MutableLiveData<Resource<List<Movie>>>()
    val movieListLiveData = moviePageLiveData.switchMap { page ->
        launchOnViewModelScope {
            _movieListLiveData.postValue(Resource.Loading())
            getMovies(page).collect { result ->
                when (result) {
                    is EitherResult.Success -> _movieListLiveData.postValue(Resource.Success(result.data))
                    is EitherResult.Failure -> _movieListLiveData.postValue(Resource.Error(result.errorEntity))
                }
            }
            _movieListLiveData
        }
    }

    fun retry() {
//        moviePageLiveData.value?.let {
//            moviePageLiveData.postValue(it)
//        }
        moviePageLiveData.postValue(1)
    }

    fun loadMore() {
        pageNumber++
        moviePageLiveData.value = pageNumber

    }


}