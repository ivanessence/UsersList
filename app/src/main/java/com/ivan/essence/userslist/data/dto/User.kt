package com.ivan.essence.userslist.data.dto

data class User(
    val albumId: Long,
    val userId: Long,
    val name: String,
    val url: String,
    val thumbnailUrl: String
)