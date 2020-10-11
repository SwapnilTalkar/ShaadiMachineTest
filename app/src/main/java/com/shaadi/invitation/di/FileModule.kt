package com.shaadi.invitation.di

import android.content.Context
import android.os.Environment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class FileModule {

    @Singleton
    @Provides
    fun provideStorageDirectory(@ApplicationContext context: Context): File {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    }
}