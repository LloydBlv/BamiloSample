package ir.zinutech.android.bamilotest.mvp.views

import ir.zinutech.android.bamilotest.commons.Product

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
interface CategoryListView : BaseView {
  fun bindCategoryList(data: ArrayList<Product>, isRefresh: Boolean)
}