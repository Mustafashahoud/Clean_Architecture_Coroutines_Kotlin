package com.android.mustafa.cleanarchapp

import android.content.Context
import com.android.mustafa.cleanarchapp.di.DaggerAppComponent
import com.android.mustafa.core.BuildConfig
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.android.mustafa.core.di.CoreComponent
import com.android.mustafa.core.di.DaggerCoreComponent
import com.android.mustafa.core.di.modules.ContextModule
import timber.log.Timber

/**
 * Base class for maintaining global application state.
 *
 * @see SplitCompatApplication
 */
class CleanArchApp : SplitCompatApplication() {

    lateinit var coreComponent: CoreComponent

//    @Inject
//    lateinit var themeUtils: ThemeUtils

    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? CleanArchApp)?.coreComponent
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initCoreDependencyInjection()
        initAppDependencyInjection()
        initRandomNightMode()
    }

    // ============================================================================================
    //  Private init methods
    // ============================================================================================

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    /**
     * Initialize log library Timber only on debug build.
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Initialize random nightMode to make developer aware of day/night themes.
     */
    private fun initRandomNightMode() {
        if (BuildConfig.DEBUG) {
//            themeUtils.setNightMode(Random.nextBoolean())
        }
    }
}
