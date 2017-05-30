package ir.zinutech.android.bamilotest.commons

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */


interface BaseResponse {
  val success: Boolean
  val session: BamiloSession?
}

data class BamiloSession(val id: String)
data class SearchListResponse(
    override val session: BamiloSession?,
    override val success: Boolean,
    val metadata: SearchMetaData) : BaseResponse


data class SearchMetaData(val sort: String?, val total_products: Int, val title: String,
    val categories: String?, val results: ArrayList<Product>?)

data class ProductMetadata(val sku: String, val name: String, val price: Long,
    val share_url: String, val image_list: ArrayList<ProductImage>,
    val specifications: ArrayList<ProdcutSpec>
)

data class ProdcutSpec(val head_label: String, val body: ArrayList<ProductBody>)

data class ProductBody(val key: String, val value: String)

data class ProductImage(val url: String, val zoom: String)

data class ProductDetailResponse(
    override val session: BamiloSession?,
    override val success: Boolean,
    val metadata: ProductMetadata
) : BaseResponse


data class Product(val sku: String, val name: String, val brand: String,
    val max_saving_percentage: Int,
    val price: Long, val special_price: Long, val image: String)