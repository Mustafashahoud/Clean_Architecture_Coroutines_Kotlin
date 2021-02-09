package com.android.mustafa.core.data.network.responses

import com.android.mustafa.core.data.database.movies.Movie

data class MoviesResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) {
  companion object {
    val empty = MoviesResponse(0, emptyList(), 0, 0)
  }
}
