package ir.zinutech.android.bamilotest.domain

import rx.Observable


/**
 * Created by najafi-mo on 10/3/2016.
 */

interface Usecase<T> {
  fun execute(vararg params: Any): Observable<T>
}
