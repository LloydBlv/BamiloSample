package ir.zinutech.android.bamilotest.models.rest

import ir.zinutech.android.bamilotest.commons.ProductDetailResponse
import ir.zinutech.android.bamilotest.commons.SearchListResponse
import ir.zinutech.android.bamilotest.models.repositories.Repository
import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 3/8/17.
 */
class RestDataSource
@Inject
constructor(mRetrofit: Retrofit) : Repository {


  private val mBamiloAPI: BamiloAPI = mRetrofit.create(BamiloAPI::class.java)

  override fun getSearchByCategory(catName: String,
      pageNum: Int): Observable<SearchListResponse> = mBamiloAPI.getSearchByCategory(catName,
      pageNum)

  override fun getProductDetail(
      sku: String): Observable<ProductDetailResponse> = mBamiloAPI.getProductDetail(sku)
}