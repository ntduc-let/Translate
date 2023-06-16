package com.ntduc.android.apps.translate.di

import androidx.media3.common.BuildConfig
import com.ntduc.android.apps.translate.constant.BASE_URL
import com.ntduc.android.apps.translate.data.remote.service.FramesService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val timeoutRead = 30   //In seconds
    private val contentType = "Content-Type"
    private val contentTypeValue = "application/json"
    private val timeoutConnect = 30   //In seconds

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        logger: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
            .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().build()

    @Provides
    @Singleton
    @Named("frames")
    fun provideFramesRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideFramesService(@Named("frames") retrofit: Retrofit): FramesService =
        retrofit.create(FramesService::class.java)
}
