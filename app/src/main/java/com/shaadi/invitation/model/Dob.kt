package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dob (

    @SerializedName("date")
    @Expose
    var date: String? = "",

    @SerializedName("age")
    @Expose
    var age: Int? = 0
)

