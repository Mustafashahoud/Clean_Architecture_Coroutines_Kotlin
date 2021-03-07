package com.android.mustafa.core.data.repository.movies.source.remote


import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.mapper.ResponseToEntityMapper
import com.android.mustafa.core.data.network.responses.MoviesResponse
import com.android.mustafa.core.data.network.services.TMDBService
import com.android.mustafa.core.util.NetworkHandler
import com.android.mustafa.domain.commons.EitherResult
import com.android.mustafa.core.util.safeApiCall
import retrofit2.Response
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
    suspend fun fetchMovies(page: Int): List<Movie> {
        return service.fetchDiscoverMovie(page).results
    }


//    private suspend fun <T, R : Any> request(apiCall: suspend () -> T, transform: (T) -> R, default: T): Result<R> {
//        return try {
//            val response = apiCall.invoke()
//            when (response.isSuccessful) {
//                true -> Result.Success(transform((response.body() ?: default)))
//                false -> Result.Error(Failure.ServerError)
//            }
//        } catch (exception: Throwable) {
//            Result.Error(Failure.ServerError)
//        }
//    }

}