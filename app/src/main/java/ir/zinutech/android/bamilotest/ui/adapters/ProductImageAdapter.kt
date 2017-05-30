package ir.zinutech.android.bamilotest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import ir.zinutech.android.bamilotest.commons.ProductImage
import ir.zinutech.android.bamilotest.ui.adapters.ProductImageAdapter.ProductViewHolder

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class ProductImageAdapter: RecyclerView.Adapter<ProductViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductViewHolder {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getItemCount(): Int {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  class ProductViewHolder(view: View, requestManager: RequestManager) : RecyclerView.ViewHolder(
      view) {
    fun bind(productImage: ProductImage) {
      with(itemView) {

      }
    }
  }
}