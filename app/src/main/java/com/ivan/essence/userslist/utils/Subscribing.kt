package com.ivan.essence.userslist.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun <T> Fragment.subscribe(
    source: Flow<T>,
    crossinline action: (T) -> Unit
) {
    source.onEach {
        action.invoke(it)
    }.launchIn(lifecycleScope)
}