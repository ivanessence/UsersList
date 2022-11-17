package com.ivan.essence.userslist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ivan.essence.userslist.R
import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.data.entities.UserPost
import com.ivan.essence.userslist.databinding.ItemPostBinding
import com.ivan.essence.userslist.databinding.ItemUserBinding

class PostsAdapter :
    ListAdapter<UserPost, PostsAdapter.PostsViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsAdapter.PostsViewHolder =
        PostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(
        holder: PostsAdapter.PostsViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class PostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserPost) = with(binding) {
            title.text = item.title
            body.text = item.body
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserPost>() {
        override fun areItemsTheSame(
            old: UserPost,
            new: UserPost
        ): Boolean =
            old.id == new.id

        override fun areContentsTheSame(
            old: UserPost,
            new: UserPost
        ): Boolean =
            old.id == new.id
    }
}