package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Location (

    @SerializedName("street")
    @Expose
    var street: Street? = Street(),

    @SerializedName("city")
    @Expose
    var city: String? = "",

    @SerializedName("state")
    @Expose
    var state: String? = "",

    @SerializedName("country")
    @Expose
    var country: String? = "",

    @SerializedName("postcode")
    var postcode: Int? = 0,

    @SerializedName("coordinates")
    var coordinates: Coordinates? = Coordinates(),

    @SerializedName("timezone")
    var timezone: Timezone? = Timezone()

)

