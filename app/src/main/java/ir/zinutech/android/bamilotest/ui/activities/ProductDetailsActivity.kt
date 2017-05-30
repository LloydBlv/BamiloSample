package ir.zinutech.android.bamilotest.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import ir.zinutech.android.bamilotest.R
import ir.zinutech.android.bamilotest.config.AppConfig
import ir.zinutech.android.bamilotest.mvp.presenters.ProductDetailPresenter
import ir.zinutech.android.bamilotest.mvp.views.ProductDetailView
import kotlinx.android.synthetic.main.activity_product_details.details_activity_brand_tv
import kotlinx.android.synthetic.main.activity_product_details.details_activity_cover_iv
import kotlinx.android.synthetic.main.activity_product_details.details_activity_price_tv
import kotlinx.android.synthetic.main.activity_product_details.details_activity_prodname_tv
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class ProductDetailsActivity: AppCompatActivity(), ProductDetailView {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }


  companion object{
    fun starter(mContext: Context, sku: String, prodName: String, brand: String, price: Long, thumb: String): Intent {
      return Intent(mContext, ProductDetailsActivity::class.java).apply {
        putExtra(EXTRA_PROD_NAME, prodName)
        putExtra(EXTRA_SKU, sku)
        putExtra(EXTRA_BRAND, brand)
        putExtra(EXTRA_PRICE, price)
        putExtra(EXTRA_THUMB, thumb)
      }
    }

    val EXTRA_PROD_NAME = "extra_prod_name"
    val EXTRA_SKU = "extra_sku"
    val EXTRA_BRAND = "extra_brand"
    val EXTRA_PRICE = "extra_price"
    val EXTRA_THUMB = "extra_thumb"
  }

  @Inject
  lateinit var mPresenter: ProductDetailPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product_details)

    AppConfig.instance.getComponent(this).inject(this)
    mPresenter.attachView(this)

    mPresenter.init(intent.getStringExtra(EXTRA_SKU))
    mPresenter.onCreate()


    details_activity_brand_tv.text = intent.getStringExtra(EXTRA_BRAND)
    details_activity_prodname_tv.text = intent.getStringExtra(EXTRA_PROD_NAME)
    details_activity_price_tv.text = "${intent.getLongExtra(EXTRA_PRICE, -1)}"

    Glide.with(this)
        .load(intent.getStringExtra(EXTRA_THUMB))
        .asBitmap()
        .into(object: BitmapImageViewTarget(details_activity_cover_iv){
          override fun onResourceReady(resource: Bitmap?,
              glideAnimation: GlideAnimation<in Bitmap>?) {
            Palette.from(resource).generate {
              it?.vibrantSwatch?.let {
                details_activity_brand_tv.setTextColor(it.titleTextColor)
                details_activity_prodname_tv.setTextColor(it.titleTextColor)
                details_activity_price_tv.setTextColor(it.titleTextColor)
              }
            }


          }
        })


  }

  override fun onLoadStarted() {}
  override fun onLoadFinished() {}
  override fun onLoadFailed(msg: Int) {}
  override fun onLoadFailed(msg: String) {}
  override fun onAllPagesLoaded() {}
  override fun onAllPagesReset() {}

}
