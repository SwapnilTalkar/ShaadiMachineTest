package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Name (

    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("first")
    @Expose
    var first: String? = "",

    @SerializedName("last")
    @Expose
    var last: String? = ""

)


