package ir.zinutech.android.bamilotest.models.rest

import ir.zinutech.android.bamilotest.commons.ProductDetailResponse
import ir.zinutech.android.bamilotest.commons.SearchListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
interface BamiloAPI {

  companion object {
    val BASE_URL = "http://www.bamilo.com/mobapi/v2.3/"
  }


  @GET("search/find/category/{categoryName}/page/{pageNum}/maxitems/36")
  fun getSearchByCategory(@Path("categoryName") catName: String,
      @Path("pageNum") pageNum: Int): Observable<SearchListResponse>


  @GET("catalog/detail/sku/{skuNum}")
  fun getProductDetail(@Path("skuNum") sku: String): Observable<ProductDetailResponse>
}