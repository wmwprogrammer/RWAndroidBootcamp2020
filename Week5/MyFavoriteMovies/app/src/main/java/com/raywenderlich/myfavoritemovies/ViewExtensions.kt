package com.raywenderlich.myfavoritemovies

import android.view.View


fun View.onClick(onClickAction: () -> Unit) {
    setOnClickListener { onClickAction() }
}