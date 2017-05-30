package ir.zinutech.android.bamilotest.mvp.presenters

import ir.zinutech.android.bamilotest.mvp.views.BaseView


/**
 * Created by mohammadrezanajafi on 3/2/16.
 */
interface BasePresenter {



  fun onStart()
  fun onStop()
  fun onPause()

  fun attachView(view: BaseView)
  fun detachView()

  fun onRefreshData(){
    onDataLoad(true)
  }
  fun onCreate(){
    onDataLoad(false)
  }

  fun onDataLoad(isRefresh: Boolean)

}
