package ir.zinutech.android.bamilotest.domain

import ir.zinutech.android.bamilotest.commons.ProductDetailResponse
import ir.zinutech.android.bamilotest.models.repositories.Repository
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class GetProductDetailUsecase
@Inject
constructor(val mRepository: Repository) : Usecase<ProductDetailResponse> {
  override fun execute(
      vararg params: Any): Observable<ProductDetailResponse> = mRepository.getProductDetail(
      params[0] as String).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}