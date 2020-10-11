package com.shaadi.invitation.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.shaadi.invitation.R
import com.shaadi.invitation.database.Profile
import com.shaadi.invitation.databinding.ActivityMainBinding
import com.shaadi.invitation.util.ApplicationLogger
import com.shaadi.invitation.util.Constants
import com.shaadi.invitation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity(), ProfileListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var glideInstance: RequestManager

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var paginationAdapter: PaginationAdapter
    private var isNewRecordLoading = false
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.profileListener = this

        snackbar = Snackbar.make(binding.mainLayout, "", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") { viewModel.requestProfile(true) }

        setupAdapter()
        viewModel.requestProfile(false)
    }


    override fun profileRequestStarted() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun profileRequestSuccess(profiles: List<Profile>) {
        binding.progressCircular.visibility = View.INVISIBLE
        paginationAdapter.setProfileList(ArrayList(profiles))
        isNewRecordLoading = false

    }

    override fun profileRequestError(error: String) {
        isNewRecordLoading = false
        binding.progressCircular.visibility = View.INVISIBLE
        ApplicationLogger.showLog(TAG, "Show Error Snack")
        snackbar = Snackbar.make(binding.mainLayout, error, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") { viewModel.requestProfile(true) }
        snackbar.show()

    }

    private fun setupAdapter(){
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        paginationAdapter = PaginationAdapter()
        binding.profileRecyclerView.layoutManager = linearLayoutManager
        binding.profileRecyclerView.adapter = paginationAdapter
        paginationAdapter.setProfileList(ArrayList())
        paginationAdapter.setActionListener(object : ProfileActionListener{
            override fun onAcceptButtonClicked(profile: Profile) {
                ApplicationLogger.showLog(TAG, "Accepted: ${profile.firstName} ${profile.lastName}")
                viewModel.updateProfileStatus(Constants.MEMBER_ACCEPTED, profile)

            }

            override fun onDeclineButtonClicked(profile: Profile) {
                ApplicationLogger.showLog(TAG, "Decline: ${profile.firstName} ${profile.lastName}")
                viewModel.updateProfileStatus(Constants.MEMBER_DECLINE, profile)
            }

            override fun hideSnackBar() {
                snackbar.dismiss()
            }

        })

        binding.profileRecyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {

                if (!isNewRecordLoading){
                    isNewRecordLoading = true

                    ApplicationLogger.showLog(TAG, "Request More Items")
                    viewModel.requestProfile(true)
                }
            }

            override val isLastPage: Boolean = false

            override val isLoading: Boolean = isNewRecordLoading
        })
    }


}