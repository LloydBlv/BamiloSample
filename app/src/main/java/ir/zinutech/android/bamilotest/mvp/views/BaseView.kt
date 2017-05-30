package ir.zinutech.android.bamilotest.mvp.views

import android.support.annotation.StringRes

/**
 * Created by mohammadrezanajafi on 10/5/16.
 */
interface BaseView {

  fun onLoadStarted()
  fun onLoadFinished()

  fun onLoadFailed(@StringRes msg: Int)
  fun onLoadFailed(msg: String)

  fun onAllPagesLoaded()
  fun onAllPagesReset()
}