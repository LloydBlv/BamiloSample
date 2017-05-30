package ir.zinutech.android.bamilotest.utils


import android.support.annotation.StringRes
import ir.zinutech.android.bamilotest.R
import retrofit2.adapter.rxjava.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by mohammadrezanajafi on 6/30/16.
 */
object ErrorHandler {
  @StringRes fun parseError(throwable: Throwable?): Int = when (throwable) {
    is UnknownHostException -> R.string.not_connected_to_internet
    is SocketTimeoutException, is TimeoutException -> R.string.connection_timeout_exception
    is HttpException -> R.string.server_error_retry
    else -> {
      Timber.d(throwable, "parseError()")
      R.string.server_error_retry
    }
  }
}
