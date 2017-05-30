package ir.zinutech.android.bamilotest.mvp.presenters

import ir.zinutech.android.bamilotest.domain.GetProductDetailUsecase
import ir.zinutech.android.bamilotest.mvp.views.BaseView
import ir.zinutech.android.bamilotest.mvp.views.ProductDetailView
import ir.zinutech.android.bamilotest.utils.ErrorHandler
import rx.Subscription
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class ProductDetailPresenter
@Inject
constructor(val mGetProductDetailUsecase: GetProductDetailUsecase): BasePresenter {

  var mGetDetailsSubscription: Subscription? = null
  var mViewReference: WeakReference<ProductDetailView>? = null

  var productId: String = ""

  fun init(prodId: String) {
    this.productId = prodId
  }


  override fun onStart() {

  }

  override fun onStop() {

  }

  override fun onPause() {

  }

  override fun attachView(view: BaseView) {
    mViewReference = WeakReference(view as ProductDetailView)

  }

  override fun detachView() {
    mViewReference?.clear()
    mGetDetailsSubscription?.unsubscribe()
  }

  override fun onDataLoad(isRefresh: Boolean) {
    loadProduct(isRefresh)
  }

  fun loadProduct(isRefresh: Boolean) {
    mViewReference?.get()?.onLoadStarted()
    mGetDetailsSubscription = mGetProductDetailUsecase.execute(productId).subscribe({

      mViewReference?.get()?.onLoadFinished()
      if (it.success) {

      } else {
        mViewReference?.get()?.onLoadFailed("COULD NOT LOAD DATA FROM SERVER")
      }

    }, {
      Timber.d(it, "product detail load failed")
      mViewReference?.get()?.onLoadFinished()
      mViewReference?.get()?.onLoadFailed(ErrorHandler.parseError(it))
    })

  }
}