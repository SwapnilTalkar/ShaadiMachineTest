package com.shaadi.invitation.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.shaadi.invitation.database.Profile
import com.shaadi.invitation.database.ProfileDatabase
import com.shaadi.invitation.model.Result
import com.shaadi.invitation.network.RetrofitApi
import com.shaadi.invitation.util.ApplicationLogger
import kotlinx.coroutines.*
import java.io.*
import java.net.URL
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val retrofitApi: RetrofitApi, private val database: ProfileDatabase, private val file: File) {

    companion object {
        const val TAG = "ProfileRepository"
    }

    suspend fun getProfile(newRecord: Boolean): List<Profile> {

        val profileCount = getProfileCount()
        ApplicationLogger.showLog(TAG, "Profile count: $profileCount")


        if (newRecord || profileCount == 0){

            val profileResponse = retrofitApi.fetchProfile()
            if (profileResponse.isSuccessful){
                ApplicationLogger.showLog(TAG, "Success")
                val results = profileResponse.body()?.results

                if (results!=null){
                    val profiles = profileResponseToProfileMapper(results)
                    downloadImages(profiles)
                }

                ApplicationLogger.showLog(TAG, "${profileResponse.body()?.info?.results}")
                return getProfileFromDb()

            }
            return getProfileFromDb()

        }else {
            return  getProfileFromDb()
        }

    }

    private fun profileResponseToProfileMapper(results: List<Result>): ArrayList<Profile> {

        val profiles = ArrayList<Profile>()
        for (indices in results.indices) {
            val result = results[indices]

            val profile = Profile(result.name?.first!!, result.name?.last!!, result.gender,
                result.dob?.age!!, result.location?.city!!, result.location?.state!!, result.location?.country!!,
                result.picture?.medium!!, "", "")

            profiles.add(profile)
        }

        return profiles
    }


    private suspend fun downloadImages(profiles: ArrayList<Profile>) {

        for (indices in profiles.indices){

            val mProfile = profiles[indices]
            withContext (Dispatchers.IO){
                val bitmap = BitmapFactory.decodeStream(URL(mProfile.webImagePath).openStream())
                val localImagePath = saveImageToInternalStorage(bitmap, profiles[indices])
                mProfile.localImagePath = localImagePath
                saveProfileToDb(mProfile)
            }

        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap, profile: Profile): String {

        val file = createImageFile(profile)

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            ApplicationLogger.showLog(TAG, "File created successfully")
        } catch (e: IOException) { // Catch the exception
            e.printStackTrace()
        }

        return file.absolutePath

    }

    @Throws(IOException::class)
    private fun createImageFile(profile: Profile): File {
        return File(file.toString(), "${profile.firstName}_${profile.lastName}.jpg")
    }

    private suspend fun saveProfileToDb(profile: Profile){
        database.getProfileDao().insertProfile(profile)
        ApplicationLogger.showLog(TAG, "Profile saved")
    }

    private suspend fun getProfileFromDb(): List<Profile> {
        return database.getProfileDao().getProfile()
    }

    private suspend fun getProfileCount(): Int {
        return database.getProfileDao().getCount()
    }

    suspend fun updateProfileStatus(status: String, profile: Profile){
        database.getProfileDao().updateProfileStatus(status, profile.firstName!!, profile.lastName!!)
    }



}