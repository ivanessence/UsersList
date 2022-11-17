package com.ivan.essence.userslist.data.network

import com.ivan.essence.userslist.data.dto.Post
import com.ivan.essence.userslist.data.dto.User
import retrofit2.Call
import retrofit2.http.GET

interface AppAPI {

    @GET("/SharminSirajudeen/test_resources/users")
    suspend fun getUsers(): List<User>

    @GET("/SharminSirajudeen/test_resources/posts")
    suspend fun getPosts(): List<Post>
}