package com.shaadi.invitation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile): Long

    @Query("Select * from profile")
    suspend fun getProfile(): List<Profile>

    @Query("Select COUNT(*) from profile")
    suspend fun getCount(): Int

    @Query("Update profile set profileStatus=:status where firstName=:firstName AND lastName=:lastName")
    suspend fun updateProfileStatus(status: String, firstName: String, lastName: String)

}