package ir.zinutech.android.bamilotest.utils

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, isAttachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, isAttachToRoot)
}


fun RecyclerView.getRowPosition(view: View?): Int? {
  val pos: Int = getChildAdapterPosition(view)
  if (pos >= 0) {
    return pos
  }
  return null
}