package com.android.mustafa.core.data.mapper

import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.network.responses.MoviesResponse
import javax.inject.Inject


/**
 * Helper class to transforms network response to model, catching the necessary data.
 */
class ResponseToEntityMapper @Inject constructor() : Mapper<MoviesResponse, List<Movie>>{
    override suspend fun map(from: MoviesResponse): List<Movie> {
        return from.results
    }
}