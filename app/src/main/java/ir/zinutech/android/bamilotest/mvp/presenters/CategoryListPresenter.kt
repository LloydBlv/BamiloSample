package ir.zinutech.android.bamilotest.mvp.presenters

import ir.zinutech.android.bamilotest.domain.GetCategorySearchUsecase
import ir.zinutech.android.bamilotest.mvp.views.BaseView
import ir.zinutech.android.bamilotest.mvp.views.CategoryListView
import ir.zinutech.android.bamilotest.utils.ErrorHandler
import rx.Subscription
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class CategoryListPresenter
@Inject
constructor(val mGetCategorySearchUsecase: GetCategorySearchUsecase): BasePresenter {

  var mSearchSubscription: Subscription? = null
  var mViewReference: WeakReference<CategoryListView>? = null

  var mCategoryName: String = ""
  var mPageNumber = 0

  val DEFAULT_SEARCH_PAGE = 1

  fun init(categoryName: String = "men_shirts", initialPageNum: Int = 1) {
    this.mCategoryName = categoryName
    this.mPageNumber = initialPageNum
  }


  override fun onStart() {

  }

  override fun onStop() {

  }

  override fun onPause() {

  }

  override fun attachView(view: BaseView) {
    mViewReference = WeakReference(view as CategoryListView)
  }

  override fun detachView() {
    mSearchSubscription?.unsubscribe()
    mViewReference?.clear()
  }

  override fun onDataLoad(isRefresh: Boolean) {
    mPageNumber = DEFAULT_SEARCH_PAGE
    loadSearchResult(isRefresh)
  }

  fun loadSearchResult(isRefresh: Boolean) {

    mViewReference?.get()?.onLoadStarted()
    mSearchSubscription = mGetCategorySearchUsecase.execute(mCategoryName, mPageNumber).subscribe({
      Timber.d("categoryList:[%s]", it)
      if (it.success && it.metadata.results != null) {
        mViewReference?.get()?.bindCategoryList(it.metadata.results, isRefresh)

      } else {
        mViewReference?.get()?.onLoadFailed(" SERVER RETURNED FALSE AS SUCCESS FLAG")
      }

      mViewReference?.get()?.onLoadFinished()

    }, {
      Timber.d(it, "while calling catListAPI")
      mViewReference?.get()?.onLoadFinished()
      mViewReference?.get()?.onLoadFailed(ErrorHandler.parseError(it))


    })



  }
}