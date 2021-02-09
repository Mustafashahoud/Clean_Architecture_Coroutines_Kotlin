package com.android.mustafa.core.di.modules

import android.content.Context
import androidx.annotation.NonNull
import com.android.mustafa.core.BuildConfig
import com.android.mustafa.core.data.network.services.TMDBService
import com.android.mustafa.core.data.network.util.RequestInterceptor
import com.android.mustafa.core.di.CoreComponent
import com.android.mustafa.core.errorHandling.NetworkHandler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
object NetworkModule {

    /**
     * Create a provider method binding for [HttpLoggingInterceptor].
     *
     * @return Instance of http interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(RequestInterceptor())
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Create a provider method binding for [Retrofit].
     *
     * @return Instance of retrofit.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(@NonNull okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.TMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Create a provider method binding for [TMDBService].
     *
     * @return Instance of TMDBService.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideTMDBService(retrofit: Retrofit): TMDBService =
        retrofit.create(TMDBService::class.java)


    @Singleton
    @Provides
    fun provideNetworkHandler(context: Context): NetworkHandler {
        return NetworkHandler(context)
    }
}
