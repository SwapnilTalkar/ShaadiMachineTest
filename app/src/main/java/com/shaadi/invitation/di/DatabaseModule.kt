package com.shaadi.invitation.di

import android.content.Context
import androidx.room.Room
import com.shaadi.invitation.database.ProfileDao
import com.shaadi.invitation.database.ProfileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProfileDatabase {
        return Room.databaseBuilder(context, ProfileDatabase::class.java, ProfileDatabase.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(profileDatabase: ProfileDatabase): ProfileDao {
        return profileDatabase.getProfileDao()
    }
}