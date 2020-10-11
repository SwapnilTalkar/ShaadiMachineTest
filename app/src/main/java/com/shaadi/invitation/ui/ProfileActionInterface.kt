package com.shaadi.invitation.ui

import com.shaadi.invitation.database.Profile

interface ProfileActionListener {

    fun onAcceptButtonClicked(profile: Profile)
    fun onDeclineButtonClicked(profile: Profile)
    fun hideSnackBar()
}