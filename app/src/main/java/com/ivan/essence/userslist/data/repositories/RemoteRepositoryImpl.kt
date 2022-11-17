package com.ivan.essence.userslist.data.repositories

import com.ivan.essence.userslist.data.dto.Post
import com.ivan.essence.userslist.data.dto.User
import com.ivan.essence.userslist.data.network.AppAPI
import com.ivan.essence.userslist.domain.repositories.RemoteRepository
import com.ivan.essence.userslist.utils.SuccessOrError
import com.ivan.essence.userslist.utils.createApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryImpl(private val api: AppAPI): RemoteRepository {

    override suspend fun fetchUsers(): Flow<SuccessOrError<List<User>, Throwable>> = flow {
        emit(
            createApiCall {
                api.getUsers()
            }
        )
    }

    override suspend fun fetchPosts(): Flow<SuccessOrError<List<Post>, Throwable>> = flow {
        emit(
            createApiCall {
                api.getPosts()
            }
        )
    }
}