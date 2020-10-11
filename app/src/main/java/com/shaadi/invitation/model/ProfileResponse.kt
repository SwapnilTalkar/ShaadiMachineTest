package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ProfileResponse (

    @SerializedName("results")
    @Expose
    var results: List<Result>? = ArrayList<Result>(),

    @SerializedName("info")
    @Expose
    var info: Info? = Info()

)


