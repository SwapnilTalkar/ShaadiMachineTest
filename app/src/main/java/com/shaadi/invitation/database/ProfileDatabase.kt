package com.shaadi.invitation.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao

    companion object{
        const val DATABASE_NAME = "profile_database.db"
    }
}