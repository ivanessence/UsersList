package com.ivan.essence.userslist.presentation.fragments.users

import androidx.core.os.bundleOf
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.ivan.essence.userslist.BuildConfig
import com.ivan.essence.userslist.R
import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.databinding.FragmentUsersBinding
import com.ivan.essence.userslist.presentation.adapters.UsersAdapter
import com.ivan.essence.userslist.presentation.base.BaseFragment
import com.ivan.essence.userslist.utils.SuccessOrError
import com.ivan.essence.userslist.utils.hide
import com.ivan.essence.userslist.utils.show
import com.ivan.essence.userslist.utils.subscribe
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>() {

    private val adapter = UsersAdapter(::onUserClick)

    override val viewModel: UsersViewModel
        get() = getViewModel()

    override val toolbar: MaterialToolbar?
        get() = binding.toolbar.appToolbar

    override fun getViewBinding(): FragmentUsersBinding =
        FragmentUsersBinding.inflate(layoutInflater)

    override fun initView() {
        setupUsersList()
        setupToolbar()
        viewModel.fetchUsers()
    }

    override fun initObservers() {
        super.initObservers()
        subscribe(viewModel.usersData) {
            with (binding) {
                when (it) {
                    is SuccessOrError.Success -> {
                        adapter.submitList(it.value)
                        progress.hide()
                        error.hide()
                    }
                    is SuccessOrError.Error -> {
                        adapter.submitList(emptyList())
                        progress.hide()
                        error.show()
                    }
                    is SuccessOrError.Loading -> {
                        progress.show()
                    }
                }
            }
        }
    }

    private fun setupUsersList() {
        with (binding) {
            usersList.adapter = adapter
            listRefreshing.setOnRefreshListener {
                listRefreshing.isRefreshing = false
                progress.show()
                viewModel.fetchUsers()
            }
        }
    }

    private fun setupToolbar() {
        toolbar?.let { toolbar ->
            toolbar.title = getString(R.string.app_name)
            toolbar.inflateMenu(R.menu.main_menu)
            toolbar.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_info -> {
                        Snackbar.make(requireView(), getString(R.string.app_version, BuildConfig.VERSION_NAME), Snackbar.LENGTH_LONG).show()
                        true
                    }
                    else -> true
                }
            }
        }
    }

    private fun onUserClick(userData: UserData) {
        navigateTo(R.id.action_users_to_user_details, bundleOf("userData" to userData ) )
    }
}