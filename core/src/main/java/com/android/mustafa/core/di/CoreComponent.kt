package com.android.mustafa.core.di

import android.content.Context
import com.android.mustafa.core.data.database.CleanArchDatabase
import com.android.mustafa.core.data.database.movies.MovieDao
import com.android.mustafa.core.data.network.services.TMDBService
import com.android.mustafa.core.di.modules.ContextModule
import com.android.mustafa.core.di.modules.DatabaseModule
import com.android.mustafa.core.di.modules.NetworkModule
import com.android.mustafa.core.di.modules.UtilsModule
import com.android.mustafa.core.util.NetworkHandler
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UtilsModule::class
    ]
)
interface CoreComponent {
    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph TMDBService
     *
     * @return TMDBService
     */
    fun tmdbService(): TMDBService

    fun cleanArchDb(): CleanArchDatabase
    fun cleanArchDao(): MovieDao

    fun networkHandler(): NetworkHandler
}
