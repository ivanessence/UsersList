package com.ivan.essence.userslist.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPost(val userId: Long, val id: Long, val title: String, val body: String): Parcelable