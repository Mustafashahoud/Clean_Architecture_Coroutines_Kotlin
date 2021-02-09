package com.android.mustafa.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.mustafa.core.data.database.converters.IntegerListConverter
import com.android.mustafa.core.data.database.converters.StringListConverter
import com.android.mustafa.core.data.database.movies.Movie
import com.android.mustafa.core.data.database.movies.MovieDao


@Database(
    entities = [Movie::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    value = [
        (StringListConverter::class),
        (IntegerListConverter::class)]
)
abstract class CleanArchDatabase : RoomDatabase() {

    /**
     * Get Movie data access object.
     *
     * @return Movie dao.
     */
    abstract fun movieDao(): MovieDao
}
