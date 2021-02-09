package com.android.mustafa.core.di.modules

import android.app.Application
import android.content.Context
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
class ContextModule(private val application: Application) {

    /**
     * Create a provider method binding for [Context].
     *
     * @return Instance of context.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideContext(): Context = application
}
