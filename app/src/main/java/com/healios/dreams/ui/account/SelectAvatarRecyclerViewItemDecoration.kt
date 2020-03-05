package com.healios.dreams.ui.account

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SelectAvatarRecyclerViewItemDecoration(private val spanCount: Int, private val spacing:Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == spanCount - 1) {
                top = spacing
            }
            left =  spacing / spanCount
            right = spacing / spanCount
            bottom = spacing
        }


    }
}