package com.android.mustafa.dynamicfeatures.movies.di

import androidx.lifecycle.ViewModelProvider
import com.android.mustafa.core.di.AppViewModelFactory
import dagger.Binds
import dagger.Module


@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}