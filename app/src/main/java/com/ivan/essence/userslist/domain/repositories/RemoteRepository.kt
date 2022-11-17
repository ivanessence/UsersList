package com.ivan.essence.userslist.domain.repositories

import com.ivan.essence.userslist.data.dto.Post
import com.ivan.essence.userslist.data.dto.User
import com.ivan.essence.userslist.utils.SuccessOrError
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun fetchUsers(): Flow<SuccessOrError<List<User>, Throwable>>

    suspend fun fetchPosts(): Flow<SuccessOrError<List<Post>, Throwable>>
}