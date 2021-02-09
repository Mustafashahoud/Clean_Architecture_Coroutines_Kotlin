package com.android.mustafa.core.di.modules

import android.content.Context
import androidx.room.Room
import com.android.mustafa.core.BuildConfig
import com.android.mustafa.core.data.database.CleanArchDatabase

import com.android.mustafa.core.data.database.migrations.MIGRATION_1_2
import com.android.mustafa.core.data.database.movies.MovieDao
import com.android.mustafa.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [CleanArchDatabase].
     *
     * @return Instance of CleanArch database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCleanArchDbDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            CleanArchDatabase::class.java,
            BuildConfig.CLEAN_ARCH_DATABASE_NAME
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [MovieDao].
     *
     * @return Instance of movie data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMovieDao(cleanArchDatabase: CleanArchDatabase) =
        cleanArchDatabase.movieDao()
//
//    /**
//     * Create a provider method binding for [CharacterFavoriteRepository].
//     *
//     * @return Instance of character favorite repository.
//     * @see Provides
//     */
//    @Singleton
//    @Provides
//    fun provideCharacterFavoriteRepository(
//        characterFavoriteDao: CharacterFavoriteDao
//    ) = CharacterFavoriteRepository(characterFavoriteDao)
}
