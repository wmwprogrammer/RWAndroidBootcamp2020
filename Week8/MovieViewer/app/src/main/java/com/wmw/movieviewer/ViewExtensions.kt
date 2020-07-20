package com.wmw.movieviewer

import android.view.View

//this came from the solution, and I implemented it for the same reason as it was created.
fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener { onClickAction() }
}
