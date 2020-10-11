package com.shaadi.invitation.di

import android.content.Context
import android.os.Environment
import com.google.gson.GsonBuilder
import com.shaadi.invitation.network.NetworkConnectionInterceptor
import com.shaadi.invitation.network.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {


    @Singleton
    @Provides
    fun provideNetworkInterceptorModule(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): RetrofitApi {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()))
            .build()
            .create(RetrofitApi::class.java)
    }
}