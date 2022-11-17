package com.ivan.essence.userslist.presentation.fragments.postDetails

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.google.android.material.appbar.MaterialToolbar
import com.ivan.essence.userslist.R
import com.ivan.essence.userslist.databinding.FragmentUserDetailsBinding
import com.ivan.essence.userslist.presentation.adapters.PostsAdapter
import com.ivan.essence.userslist.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding, UserDetailsViewModel>() {

    private val adapter = PostsAdapter()
    private val args: UserDetailsFragmentArgs by navArgs()

    override val viewModel: UserDetailsViewModel
        get() = getViewModel()

    override val toolbar: MaterialToolbar?
        get() = binding.toolbar.appToolbar

    override fun getViewBinding(): FragmentUserDetailsBinding =
        FragmentUserDetailsBinding.inflate(layoutInflater)

    override fun initView() {
        setupUserAvatar()
        setupPostsList()
        setupToolbar()
    }

    override fun initObservers() {
        super.initObservers()
    }

    private fun setupPostsList() {
        with(binding) {
            postsList.adapter = adapter
            adapter.submitList(args.userData.posts)
        }
    }

    private fun setupToolbar() {
        toolbar?.let { toolbar ->
            toolbar.title = getString(R.string.list_of_posts, args.userData.name)
            toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupUserAvatar() {
        Glide
            .with(requireContext())
            .load(args.userData.url)
            .override(SIZE_ORIGINAL)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_user_placeholder)
            .into(binding.userAvatar)
    }
}