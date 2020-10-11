package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Timezone {
    @SerializedName("offset")
    @Expose
    var offset: String? = ""

    @SerializedName("description")
    @Expose
    var description: String? = ""
}

