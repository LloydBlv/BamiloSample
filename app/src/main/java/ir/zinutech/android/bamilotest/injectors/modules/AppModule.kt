package ir.zinutech.android.bamilotest.injectors.modules

import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.zinutech.android.bamilotest.models.repositories.Repository
import ir.zinutech.android.bamilotest.models.rest.BamiloAPI
import ir.zinutech.android.bamilotest.models.rest.RestDataSource
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by mohammadrezanajafi on 2/28/16.
 */

@Module
class AppModule
constructor(private val mContext: Context) {

  @Provides
  fun provideContext(): Context {
    return mContext
  }

  @Provides fun provideSensorManager(context: Context): SensorManager {
    return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
  }

  @Provides fun provideNotificationManager(context: Context): NotificationManager {
    return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  }


  @Provides
  fun provideConnectivityManager(context: Context): ConnectivityManager {
    return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  }

  @Provides
  fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
  }

  @Provides
  fun provideCompositeSubscription(): CompositeSubscription {
    return CompositeSubscription()
  }

  @Provides
  @Singleton
  internal fun provideOkHttpCache(application: Context): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
  }


  @Singleton
  @Provides
  fun provideGson(): Gson =
      GsonBuilder().create()

  @Singleton
  @Provides
  fun provideHttpClient(cache: Cache) =
      Builder()
          .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
          .cache(cache)
          .connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .writeTimeout(30, TimeUnit.SECONDS)
          .build()

  @Singleton
  @Provides
  fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
      Retrofit.Builder()
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .baseUrl(BamiloAPI.BASE_URL)
          .client(okHttpClient)
          .build()

  @Singleton
  @Provides
  fun provideDataRepository(restDataSource: RestDataSource): Repository = restDataSource


  companion object {

    internal val SHARED_PREF_NAME = "preferences"
    val REQUEST_TIMEOUT: Long = 15
  }
}


