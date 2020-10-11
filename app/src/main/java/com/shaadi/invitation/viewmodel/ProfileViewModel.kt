package com.shaadi.invitation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaadi.invitation.database.Profile
import com.shaadi.invitation.network.NoInternetConnectivityException
import com.shaadi.invitation.repository.ProfileRepository
import com.shaadi.invitation.ui.ProfileListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {


    companion object {
        const val TAG = "ProfileViewModel"
    }

    lateinit var profileListener: ProfileListener

    fun requestProfile(newRecord: Boolean) {

        profileListener.profileRequestStarted()
        viewModelScope.launch {

            try {
                val profiles = getProfiles(newRecord)
                profileListener.profileRequestSuccess(profiles)

            } catch (e: NoInternetConnectivityException){
                profileListener.profileRequestError(e.message!!)
            }

        }

    }

    private suspend fun getProfiles(newRecord: Boolean): List<Profile>{

        return withContext(Dispatchers.IO){
            profileRepository.getProfile(newRecord)
        }
    }

    fun updateProfileStatus(status: String, profile: Profile){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                profileRepository.updateProfileStatus(status, profile)
            }
        }

    }
}