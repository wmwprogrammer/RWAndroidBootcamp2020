package com.raywenderlich.myfavoritemovies

import android.view.View

//this came from the solution, and I implemented it for the same reason as it was created.
fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener { onClickAction() }
}

fun View.longPress(onLongPressAction: () -> Boolean) {
    setOnLongClickListener { onLongPressAction() }
}