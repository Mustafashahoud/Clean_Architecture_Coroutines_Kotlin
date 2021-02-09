package com.android.mustafa.cleanarchapp.di

import com.android.mustafa.cleanarchapp.CleanArchApp
import com.android.mustafa.core.di.CoreComponent
import com.android.mustafa.core.di.scopes.AppScope
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: CleanArchApp)
}
