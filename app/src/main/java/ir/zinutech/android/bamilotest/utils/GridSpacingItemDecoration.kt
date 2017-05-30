package ir.zinutech.android.bamilotest.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.view.View

/**
 * Created by najafi-mo on 10/19/2016.
 */

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
      state: State?) {
    val position = parent.getChildAdapterPosition(view) // item position
    val column = position % spanCount // item column

    if (includeEdge) {
      outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
      outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

      if (position < spanCount) { // top edge
        outRect.top = spacing
      }
      outRect.bottom = spacing // item bottom
    } else {
      outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
      outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
      if (position >= spanCount) {
        outRect.top = spacing // item top
      }
    }
  }
}