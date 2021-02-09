package com.android.mustafa.dynamicfeatures.movies.di

import androidx.lifecycle.ViewModel
import com.android.mustafa.core.data.repository.movies.MoviesRepositoryImpl
import com.android.mustafa.core.di.ViewModelKey
import com.android.mustafa.core.di.scopes.FeatureScope
import com.android.mustafa.core.domain.movies.repository.MoviesRepository
import com.android.mustafa.dynamicfeatures.movies.ui.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MoviesModule {
    @FeatureScope
    @Binds
    fun provideMovieRepository(MoviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel
}