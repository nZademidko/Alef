package com.inter.testalef.ui.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ImageVerticalItemDecoration(
    private val innerDivider: Int,
    private val outerDivider: Int,
    private val rowCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter ?: return
        val curPosition = parent.getChildAdapterPosition(view)
            .takeIf { it != RecyclerView.NO_POSITION } ?: return

        val isPrevTargetView = adapter.isPrevTargetView(curPosition)
        val isNextTargetView = adapter.isNextTargetView(curPosition)

        with(outRect) {
            top = if (isPrevTargetView) innerDivider else outerDivider
            bottom = if (isNextTargetView) innerDivider else outerDivider
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(
        curPosition: Int
    ) = curPosition >= rowCount

    private fun RecyclerView.Adapter<*>.isNextTargetView(
        curPosition: Int
    ) = curPosition <= itemCount - 1 - itemCount % rowCount
}