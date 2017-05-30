package ir.zinutech.android.bamilotest.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import ir.zinutech.android.bamilotest.R
import ir.zinutech.android.bamilotest.commons.Product
import ir.zinutech.android.bamilotest.ui.adapters.CategoryListAdapter.CategoryViewHolder
import ir.zinutech.android.bamilotest.utils.inflate
import kotlinx.android.synthetic.main.item_categorylist_layout.view.categorylist_item_brand_tv
import kotlinx.android.synthetic.main.item_categorylist_layout.view.categorylist_item_price_tv
import kotlinx.android.synthetic.main.item_categorylist_layout.view.categorylist_item_productname_tv
import kotlinx.android.synthetic.main.item_categorylist_layout.view.categorylist_item_thumb_iv

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class CategoryListAdapter(val requestManager: RequestManager, val onItemClicked: (rootView: View) -> Unit): RecyclerView.Adapter<CategoryViewHolder>() {


  val mItems: ArrayList<Product> = ArrayList()


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder = CategoryViewHolder(
    parent.inflate(R.layout.item_categorylist_layout).apply {
      setOnClickListener {
        onItemClicked(it)
      }
    }
  , requestManager)

  override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
    holder?.bind(mItems[position])
  }

  fun addAll(newData: ArrayList<Product>) {
    val lastIndex = mItems.lastIndex
    mItems.addAll(newData)
    notifyItemRangeInserted(lastIndex, newData.size)
  }

  fun clear() {
    mItems.clear()
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = mItems.size

  class CategoryViewHolder(view: View,
      val requestManager: RequestManager): ViewHolder(view){

    val placeHolder = ColorDrawable(Color.GRAY)
    val errorHolder = ColorDrawable(Color.DKGRAY)
    fun bind(product: Product) {
      with(itemView) {
        categorylist_item_brand_tv.text = product.brand
        categorylist_item_productname_tv.text = product.name
        categorylist_item_price_tv.text = "${product.price}"

        val dimension = resources.getDimension(R.dimen.product_item_width).toInt()
        requestManager.load(product.image)
            .fitCenter()
            .override(dimension, dimension)
            .diskCacheStrategy(ALL) /* in order to faster cover load in details activity*/
            .placeholder(placeHolder)
            .error(errorHolder)
            .into(categorylist_item_thumb_iv)

      }
    }
  }
}