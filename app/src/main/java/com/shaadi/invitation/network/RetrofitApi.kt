package com.shaadi.invitation.network

import com.shaadi.invitation.model.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("api/?results=10")
    suspend fun fetchProfile() : Response<ProfileResponse>

}