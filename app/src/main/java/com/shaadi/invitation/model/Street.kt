package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Street (

    @SerializedName("number")
    var number: Int? = 0,

    @SerializedName("name")
    @Expose
    var name: String? = ""
)

