package com.shaadi.invitation.di

import com.shaadi.invitation.database.ProfileDatabase
import com.shaadi.invitation.network.RetrofitApi
import com.shaadi.invitation.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.io.File
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ProfileRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api: RetrofitApi, database: ProfileDatabase, file: File): ProfileRepository {
        return ProfileRepository(api, database, file)
    }

}