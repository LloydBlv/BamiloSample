package ir.zinutech.android.bamilotest.domain

import ir.zinutech.android.bamilotest.commons.SearchListResponse
import ir.zinutech.android.bamilotest.models.repositories.Repository
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class GetCategorySearchUsecase
@Inject
constructor(val mRepository: Repository) : Usecase<SearchListResponse> {
  override fun execute(
      vararg params: Any): Observable<SearchListResponse> = mRepository.getSearchByCategory(
      params[0] as String, params[1] as Int).subscribeOn(Schedulers.io()).observeOn(
      AndroidSchedulers.mainThread())
}