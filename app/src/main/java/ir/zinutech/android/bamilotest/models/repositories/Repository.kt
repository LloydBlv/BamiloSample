package ir.zinutech.android.bamilotest.models.repositories

import ir.zinutech.android.bamilotest.commons.ProductDetailResponse
import ir.zinutech.android.bamilotest.commons.SearchListResponse
import rx.Observable

/**
 * Created by mohammadrezanajafi on 3/8/17.
 */
interface Repository {
  fun getSearchByCategory(catName: String, pageNum: Int): Observable<SearchListResponse>
  fun getProductDetail(sku: String): Observable<ProductDetailResponse>
}
