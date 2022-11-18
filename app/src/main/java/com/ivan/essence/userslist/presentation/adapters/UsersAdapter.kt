package com.ivan.essence.userslist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ivan.essence.userslist.R
import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.databinding.ItemUserBinding

class UsersAdapter(private val onClick: (userData: UserData) -> Unit) :
    ListAdapter<UserData, UsersAdapter.UsersViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder =
        UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(
        holder: UsersAdapter.UsersViewHolder,
        position: Int
    ) {
        with(holder) {
            itemView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
            bind(getItem(position))
        }
    }

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserData) = with(binding) {
            loadAvatar(item.thumbnailUrl, this)
            userName.text = item.name
            userPostsAmount.text = root.context.getString(R.string.user_posts_count, item.posts.size)
            root.setOnClickListener {
                onClick(item)
            }
        }

        private fun loadAvatar(avatarLink: String, binding: ItemUserBinding) {
            Glide
                .with(binding.root.context)
                .load(avatarLink)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_user_placeholder)
                .into(binding.userAvatar)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(
            old: UserData,
            new: UserData
        ): Boolean =
            old.userId == new.userId

        override fun areContentsTheSame(
            old: UserData,
            new: UserData
        ): Boolean =
            old.userId == new.userId
    }
}