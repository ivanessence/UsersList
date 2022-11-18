package com.ivan.essence.userslist.presentation.fragments.users

import androidx.lifecycle.viewModelScope
import com.ivan.essence.userslist.data.entities.UserData
import com.ivan.essence.userslist.data.entities.UserPost
import com.ivan.essence.userslist.domain.usecases.PostsUseCase
import com.ivan.essence.userslist.domain.usecases.UsersUseCase
import com.ivan.essence.userslist.presentation.base.BaseViewModel
import com.ivan.essence.userslist.utils.SuccessOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList
import java.util.Queue

class UsersViewModel(
    private val usersUseCase: UsersUseCase,
    private val postsUseCase: PostsUseCase
) : BaseViewModel() {

    private val _usersData =
        MutableSharedFlow<SuccessOrError<List<UserData>, Throwable>>(replay = 1)
    val usersData get() = _usersData

    fun fetchUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                usersData.emit(SuccessOrError.Loading)
                val usersRequest = async { usersUseCase.invoke() }
                val postsRequest = async { postsUseCase.invoke() }
                val usersResult = usersRequest.await()
                val postsResult = postsRequest.await()
                if (usersResult is SuccessOrError.Success
                    &&
                    postsResult is SuccessOrError.Success
                ) {
                    _usersData.emit(
                        SuccessOrError.Success(
                            getMatchedUsersWithPosts(
                                usersResult.value,
                                postsResult.value
                            )
                        )
                    )
                } else usersData.emit(SuccessOrError.Error(Throwable()))
            }
        }
    }

    private fun getMatchedUsersWithPosts(usersData: List<UserData>, posts: List<UserPost>): List<UserData> {
        val usersId = usersData.map {
            it.userId
        }
        val filteredPosts = posts.sortedBy {
            it.userId
        }
        val postsQueue: Queue<UserPost> = LinkedList(filteredPosts)
        var userPosition = 0
        var skipIteration = false
        var post: UserPost? = null
        usersId.forEach { userId ->
            val userPostsList = mutableListOf<UserPost>()
            if (!skipIteration) {
                post = postsQueue.poll()
            }
            while (userId == post?.userId) {
                userPostsList.add(post!!)
                post = postsQueue.poll()
            }
            usersData[userPosition].posts.addAll(userPostsList)
            userPosition += 1
            skipIteration = true
        }
        return usersData
    }
}