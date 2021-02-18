package com.android.mustafa.dynamicfeatures.movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.android.mustafa.commons.ui.ViewModelBase
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.domain.base.Result
import com.android.mustafa.core.domain.movies.usecase.GetMovies
import kotlinx.coroutines.Dispatchers
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
            when (val result = getMovies.run(page)) {
                is Result.Success -> _movieListLiveData.postValue(Resource.Success(result.data))
                is Result.Error -> _movieListLiveData.postValue(Resource.Error(result.error))
            }
            _movieListLiveData
        }
    }

    fun postPage(pageNumber: Int) {
        moviePageLiveData.postValue(pageNumber)
    }


}