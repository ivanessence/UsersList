package com.ivan.essence.userslist.utils

import android.view.View

//View
fun View.show() {
    visibility = View.VISIBLE
}

fun View.partlyHide() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}