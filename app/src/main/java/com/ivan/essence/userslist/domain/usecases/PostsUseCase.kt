package com.ivan.essence.userslist.domain.usecases

import com.ivan.essence.userslist.data.dto.Post
import com.ivan.essence.userslist.data.entities.UserPost
import com.ivan.essence.userslist.domain.repositories.RemoteRepository
import com.ivan.essence.userslist.utils.SuccessOrError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transform

class PostsUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun invoke(): SuccessOrError<List<UserPost>, Throwable> {
        return remoteRepository.fetchPosts().transform { result ->
            emit(
                result.mapResult { it.map { post ->
                    UserPost(
                        userId = post.userId,
                        id = post.id,
                        title = post.title,
                        body = post.body
                    )
                } }
            )
        }.first()
    }
}