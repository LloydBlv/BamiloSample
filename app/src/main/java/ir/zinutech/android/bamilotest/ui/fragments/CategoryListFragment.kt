package ir.zinutech.android.bamilotest.ui.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ir.zinutech.android.bamilotest.R
import ir.zinutech.android.bamilotest.commons.Product
import ir.zinutech.android.bamilotest.config.AppConfig
import ir.zinutech.android.bamilotest.mvp.presenters.CategoryListPresenter
import ir.zinutech.android.bamilotest.mvp.views.CategoryListView
import ir.zinutech.android.bamilotest.ui.activities.ProductDetailsActivity
import ir.zinutech.android.bamilotest.ui.adapters.CategoryListAdapter
import ir.zinutech.android.bamilotest.utils.GridSpacingItemDecoration
import ir.zinutech.android.bamilotest.utils.getRowPosition
import kotlinx.android.synthetic.main.fragment_category_list_layout.category_fragment_rv
import kotlinx.android.synthetic.main.fragment_category_list_layout.category_fragment_swiperefresh
import kotlinx.android.synthetic.main.fragment_category_list_layout.root_categorylist_layout
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class CategoryListFragment : Fragment(), CategoryListView {

  companion object {
    fun newInstance(categoryName: String): CategoryListFragment {
      val args = Bundle()
      args.putString(ARG_CATEGORY_NAME, categoryName)
      return CategoryListFragment().apply {
        arguments = args
      }
    }

    val ARG_CATEGORY_NAME = "arg_category_name"
  }

  @Inject
  lateinit var mPresenter: CategoryListPresenter

  var mCategoryListAdapter: CategoryListAdapter? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? = inflater?.inflate(
      R.layout.fragment_category_list_layout, container, false)

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    AppConfig.instance.getComponent(this).inject(this)
    mPresenter.init(categoryName = arguments.getString(ARG_CATEGORY_NAME))
    mPresenter.attachView(this)


    category_fragment_rv.apply {
      setHasFixedSize(true)
      layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
      addItemDecoration(GridSpacingItemDecoration(2, 5, true))
      mCategoryListAdapter = CategoryListAdapter(Glide.with(this@CategoryListFragment), {
        getRowPosition(it)?.let {
          mCategoryListAdapter?.mItems?.get(it)?.let {
            with(it) {
              startActivity(ProductDetailsActivity.starter(mContext = activity,
                  sku = sku,
                  prodName = name,
                  brand = brand,
                  price = price,
                  thumb = image
                  ))
            }
          }

        }
      })

      adapter = mCategoryListAdapter
    }

    category_fragment_swiperefresh.apply {
      setOnRefreshListener {
        mPresenter.onRefreshData()
      }
    }
    mPresenter.onCreate()
  }

  override fun onDestroyView() {
    mPresenter.detachView()
    super.onDestroyView()
  }


  override fun onLoadStarted() {
    category_fragment_swiperefresh.isRefreshing = true
  }
  override fun onLoadFinished() {
    category_fragment_swiperefresh.isRefreshing = false
  }
  override fun onLoadFailed(msg: Int) {
    onLoadFailed(getString(msg))
  }
  override fun onLoadFailed(msg: String) {
    Snackbar.make(root_categorylist_layout, msg, Snackbar.LENGTH_INDEFINITE)
        .setAction(R.string.retry, { mPresenter.onRefreshData() })
        .show()
  }
  override fun onAllPagesLoaded() {}
  override fun onAllPagesReset() {}

  override fun bindCategoryList(data: ArrayList<Product>, isRefresh: Boolean) {
    if (isRefresh) {
      mCategoryListAdapter?.clear()
    }
    mCategoryListAdapter?.addAll(data)
  }
}