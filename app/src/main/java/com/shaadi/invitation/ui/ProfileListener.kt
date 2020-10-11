package com.shaadi.invitation.ui

import com.shaadi.invitation.database.Profile

interface ProfileListener {

    fun profileRequestStarted()
    fun profileRequestSuccess(profiles: List<Profile>)
    fun profileRequestError(error: String)
}