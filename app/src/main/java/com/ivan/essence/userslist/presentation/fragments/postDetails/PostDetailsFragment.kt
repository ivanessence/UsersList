package com.ivan.essence.userslist.presentation.fragments.postDetails

import android.os.Build
import com.google.android.material.appbar.MaterialToolbar
import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.databinding.FragmentUserDetailsBinding
import com.ivan.essence.userslist.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PostDetailsFragment : BaseFragment<FragmentUserDetailsBinding, PostDetailsViewModel>() {

    override val viewModel: PostDetailsViewModel
        get() = getViewModel()

    override val toolbar: MaterialToolbar?
        get() = binding.toolbar.appToolbar

    override fun getViewBinding(): FragmentUserDetailsBinding =
        FragmentUserDetailsBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun initObservers() {
        super.initObservers()
    }
}