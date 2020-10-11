package com.shaadi.invitation.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile (

    var firstName: String?,
    var lastName: String?,
    var gender: String?,
    var age: Int?,
    var city: String?,
    var state: String?,
    var country: String?,
    var webImagePath: String?,
    var localImagePath: String,
    var profileStatus: String
){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}