package com.shaadi.invitation.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.shaadi.invitation.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class GlideModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(requestOptions)
    }
}