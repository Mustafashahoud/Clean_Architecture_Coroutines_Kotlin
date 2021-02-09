package com.android.mustafa.core.data.repository.movies.source.remote


import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.mapper.ResponseToEntityMapper
import com.android.mustafa.core.data.network.responses.MoviesResponse
import com.android.mustafa.core.data.network.services.TMDBService
import com.android.mustafa.core.errorHandling.Failure
import com.android.mustafa.core.errorHandling.NetworkHandler
import com.android.mustafa.core.domain.base.Result
import retrofit2.Call
import javax.inject.Inject

/**
 * Retrieve data from the TMDB service.
 */
class MoviesRemoteDataSource @Inject constructor(
    private val service: TMDBService,
    private val networkHandler: NetworkHandler,
    private val mapper: ResponseToEntityMapper
) {
    /**
     * Retrieve a list of  [Movie] instances from the API
     */
    fun fetchMovies(page: Int): Result<List<Movie>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(service.fetchDiscoverMovie(page), { it.results }, MoviesResponse.empty)
            false ->  Result.Error(Failure.NetworkError)
        }
    }


    private fun <T, R : Any> request(call: Call<T>, transform: (T) -> R, default: T): Result<R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Result.Success(transform((response.body() ?: default)))
                false -> Result.Error(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Result.Error(Failure.ServerError)
        }
    }

}