package com.ivan.essence.userslist.domain.usecases

import com.ivan.essence.userslist.data.dto.User
import com.ivan.essence.userslist.domain.repositories.RemoteRepository
import com.ivan.essence.userslist.utils.SuccessOrError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transform

class UsersUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun invoke(): SuccessOrError<List<User>, Throwable> {
         return remoteRepository.fetchUsers().transform { result ->
            emit(
                result.mapSuccess { it }.toSuccessOrError()
            )
        }.first()
    }
}