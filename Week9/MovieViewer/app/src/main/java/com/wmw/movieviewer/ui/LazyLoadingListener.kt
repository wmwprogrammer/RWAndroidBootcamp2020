package com.wmw.movieviewer.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

//Listener came from the solution
class LazyLoadingListener(
    private inline val onLastItemReached: () -> Unit
) : RecyclerView.OnScrollListener() {

    private val threshold = 20

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val manager = recyclerView.layoutManager as LinearLayoutManager

        if (abs(dy) > threshold && hasReachedEnd(manager, recyclerView)) {
            onLastItemReached()
        }
    }

    private fun hasReachedEnd(manager: LinearLayoutManager?, recyclerView: RecyclerView): Boolean {
        val adapter = recyclerView.adapter

        if (manager != null && adapter != null) {
            val pos = manager.findLastVisibleItemPosition()
            val numItems = adapter.itemCount
            return pos >= numItems - 5
        }

        return false
    }
}