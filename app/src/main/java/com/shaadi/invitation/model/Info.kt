package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Info (

    @SerializedName("seed")
    @Expose
    var seed: String? = "",

    @SerializedName("results")
    @Expose
    var results: Int? = 0,

    @SerializedName("page")
    @Expose
    var page: Int? = 0,

    @SerializedName("version")
    @Expose
    var version: String? = ""

    )

