package com.android.mustafa.core.data.database.movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    var id: Int,
    var page: Int,
//    var keywords: List<Keyword>? = ArrayList(),
//    var videos: List<Video>? = ArrayList(),
//    var reviews: List<Review>? = ArrayList(),
    val poster_path: String?,
    val adult: Boolean,
    val overview: String?,
    val release_date: String?,
    var genre_ids: List<Int>,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
    var search: Boolean?,
    var filter: Boolean?
)
