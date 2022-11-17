package com.ivan.essence.userslist.presentation.fragments.users

import com.ivan.essence.userslist.data.dto.User
import com.ivan.essence.userslist.domain.usecases.PostsUseCase
import com.ivan.essence.userslist.domain.usecases.UsersUseCase
import com.ivan.essence.userslist.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

class UsersViewModel(
    private val usersUseCase: UsersUseCase,
    private val postsUseCase: PostsUseCase
) : BaseViewModel() {

    private val _usersData = MutableSharedFlow<List<User>>(replay = 1)
    val usersData get() = _usersData

    private val _postsData = MutableSharedFlow<List<User>>(replay = 1)
    val postsData get() = _postsData

}