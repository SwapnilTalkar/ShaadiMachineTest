package com.shaadi.invitation.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.shaadi.invitation.R
import com.shaadi.invitation.database.Profile
import com.shaadi.invitation.util.Constants
import java.io.File

class PaginationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var profileList =  ArrayList<Profile>()
    private var isLoadingAdded = false
    private lateinit var profileActionListener: ProfileActionListener

    fun setActionListener(profileListener: ProfileActionListener){
        this.profileActionListener = profileListener
    }

    fun setProfileList(profileList: ArrayList<Profile>) {
        this.profileList = profileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val viewItem: View = inflater.inflate(R.layout.single_profile_row, parent, false)
                viewHolder = ProfileViewHolder(viewItem)
            }
            LOADING -> {
                val viewLoading: View = inflater.inflate(R.layout.item_progress, parent, false)
                viewHolder = LoadingViewHolder(viewLoading)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profile = profileList[position]
        when (getItemViewType(position)) {
            ITEM -> {
                val profileViewHolder = holder as ProfileViewHolder
                profileViewHolder.profileImageView.setImageBitmap(getBitmapFromStorage(profile.localImagePath))
                profileViewHolder.profileNameTv.text = profile.firstName!!.plus(" ${profile.lastName}")
                profileViewHolder.profileDescriptionTv.text = "${profile.age}, ${profile.gender} \n ${profile.city}, ${profile.state}, ${profile.country}"


                if (profile.profileStatus.isEmpty()){
                    profileViewHolder.profileDeclineButton.visibility = View.VISIBLE
                    profileViewHolder.profileAcceptButton.visibility = View.VISIBLE
                    profileViewHolder.profileStatusTv.visibility = View.INVISIBLE
                }else {
                    profileViewHolder.profileDeclineButton.visibility = View.INVISIBLE
                    profileViewHolder.profileAcceptButton.visibility = View.INVISIBLE
                    profileViewHolder.profileStatusTv.text = profile.profileStatus
                    profileViewHolder.profileStatusTv.visibility = View.VISIBLE
                }

                profileViewHolder.profileAcceptButton.setOnClickListener {
                    profileActionListener.onAcceptButtonClicked(profile)
                    updateProfileList(position, true )
                }
                profileViewHolder.profileDeclineButton.setOnClickListener {
                    profileActionListener.onDeclineButtonClicked(profile)
                    updateProfileList(position, false)
                }

                if (position!=profileList.size-1){
                    profileActionListener.hideSnackBar()
                }

            }
            LOADING -> {
                val loadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == profileList.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val profileImageView = itemView.findViewById(R.id.profile_iv) as ShapeableImageView
        val profileNameTv = itemView.findViewById(R.id.profile_name_tv) as AppCompatTextView
        val profileDescriptionTv = itemView.findViewById(R.id.profile_description_tv) as AppCompatTextView

        val profileStatusTv = itemView.findViewById(R.id.status_tv) as AppCompatTextView
        val profileDeclineButton = itemView.findViewById(R.id.decline_btn) as MaterialButton
        val profileAcceptButton = itemView.findViewById(R.id.accept_btn) as MaterialButton

    }

    private fun getBitmapFromStorage(localImagePath: String): Bitmap? {

        val image = File(localImagePath)
        val bmOptions = BitmapFactory.Options()
        return  BitmapFactory.decodeFile(image.absolutePath, bmOptions)
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.loadmore_progress)

    }

    companion object {
        private const val LOADING = 0
        private const val ITEM = 1
    }


    private fun updateProfileList(position: Int, isAccepted: Boolean){

        val profile = profileList[position]
        if (isAccepted){
            profile.profileStatus =  Constants.MEMBER_ACCEPTED
        }else{
            profile.profileStatus =  Constants.MEMBER_DECLINE
        }

        profileList[position] = profile
        notifyDataSetChanged()
    }

}