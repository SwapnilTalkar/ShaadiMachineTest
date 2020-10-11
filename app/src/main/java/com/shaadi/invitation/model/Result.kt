package com.shaadi.invitation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Result {
    @SerializedName("gender")
    @Expose
    var gender: String? = ""

    @SerializedName("name")
    @Expose
    var name: Name? = Name()

    @SerializedName("location")
    @Expose
    var location: Location? = Location()

    @SerializedName("email")
    var email: String? = ""

    @SerializedName("login")
    var login: Login? = Login()

    @SerializedName("dob")
    @Expose
    var dob: Dob? = Dob()

    @SerializedName("registered")
    @Expose
    var registered: Registered? = Registered()

    @SerializedName("phone")
    @Expose
    var phone: String? = ""

    @SerializedName("cell")
    var cell: String? = ""

    @SerializedName("id")
    @Expose
    var id: Id? = Id()

    @SerializedName("picture")
    @Expose
    var picture: Picture? = Picture()

    @SerializedName("nat")
    var nat: String? = ""
}