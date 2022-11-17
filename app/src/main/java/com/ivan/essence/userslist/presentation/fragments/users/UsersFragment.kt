package com.ivan.essence.userslist.presentation.fragments.users

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.ivan.essence.userslist.R
import com.ivan.essence.userslist.databinding.FragmentUsersBinding
import com.ivan.essence.userslist.presentation.base.BaseFragment
import com.ivan.essence.userslist.utils.subscribe
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>() {

    override val viewModel: UsersViewModel
        get() = getViewModel()

    override fun getViewBinding(): FragmentUsersBinding =
        FragmentUsersBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun initObservers() {
        super.initObservers()
        subscribe(viewModel.usersData) {

        }
        subscribe(viewModel.postsData) {

        }
    }
}