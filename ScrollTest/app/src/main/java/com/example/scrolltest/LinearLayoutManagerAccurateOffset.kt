package com.example.scrolltest

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearLayoutManagerAccurateOffset(context: Context?) : LinearLayoutManager(context) {

    // map of child adapter position to its height.
    private val childSizesMap = mutableMapOf<Int, Int>()

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            childSizesMap[getPosition(child!!)] = child.height
        }
    }

     fun computeVerticalScrollOffsett(state: RecyclerView.State?): Int {
        if (childCount == 0) {
            return 0
        }
        val firstChild = getChildAt(0)
        val firstChildPosition = getPosition(firstChild!!)
        var scrolledY: Int = -firstChild.y.toInt()
        for (i in 0 until firstChildPosition) {
            scrolledY += childSizesMap[i] ?: 0
        }
        return scrolledY
    }

}