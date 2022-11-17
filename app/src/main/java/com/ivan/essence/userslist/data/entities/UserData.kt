package com.ivan.essence.userslist.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userId: Long,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    val posts: MutableList<UserPost>
): Parcelable