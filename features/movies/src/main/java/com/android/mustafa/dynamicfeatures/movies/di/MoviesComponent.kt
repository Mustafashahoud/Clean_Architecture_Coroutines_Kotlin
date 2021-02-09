package com.android.mustafa.dynamicfeatures.movies.di

import com.android.mustafa.core.di.CoreComponent
import com.android.mustafa.core.di.scopes.FeatureScope
import com.android.mustafa.dynamicfeatures.movies.ui.MoviesFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        MoviesModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface MoviesComponent {

    /**
     * Inject dependencies on component.
     *
     * @param moviesFragment moviesFragment.
     */
    fun inject(moviesFragment: MoviesFragment)
}
