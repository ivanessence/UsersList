package com.ivan.essence.userslist.domain.usecases

import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.domain.repositories.RemoteRepository
import com.ivan.essence.userslist.utils.SuccessOrError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transform

class UsersUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun invoke(): SuccessOrError<List<UserData>, Throwable> {
        return remoteRepository.fetchUsers().transform { result ->
            emit(
                result.mapResult { it.map { dto ->
                    UserData(
                        userId = dto.userId,
                        name = dto.name,
                        url = dto.url,
                        thumbnailUrl = dto.thumbnailUrl,
                        mutableListOf()
                    )
                } }
            )
        }.first()
    }
}