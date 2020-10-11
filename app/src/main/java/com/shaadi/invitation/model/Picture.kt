package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Picture (

    @SerializedName("large")
    @Expose
    var large: String? = "",

    @SerializedName("medium")
    @Expose
    var medium: String? = "",

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = ""
)


