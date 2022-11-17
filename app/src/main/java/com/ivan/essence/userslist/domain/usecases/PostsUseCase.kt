package com.ivan.essence.userslist.domain.usecases

import com.ivan.essence.userslist.domain.repositories.RemoteRepository

class PostsUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun fetchPosts() {

    }
}