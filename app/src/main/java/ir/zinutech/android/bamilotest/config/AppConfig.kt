package ir.zinutech.android.bamilotest.config

import android.app.Application
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ir.zinutech.android.bamilotest.BuildConfig
import ir.zinutech.android.bamilotest.R
import ir.zinutech.android.bamilotest.injectors.components.ActivityComponent
import ir.zinutech.android.bamilotest.injectors.components.AppComponent
import ir.zinutech.android.bamilotest.injectors.components.DaggerAppComponent
import ir.zinutech.android.bamilotest.injectors.components.FragmentComponent
import ir.zinutech.android.bamilotest.injectors.modules.ActivityModule
import ir.zinutech.android.bamilotest.injectors.modules.AppModule
import ir.zinutech.android.bamilotest.injectors.modules.FragmentModule
import timber.log.Timber
import timber.log.Timber.DebugTree
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import kotlin.properties.Delegates

/**
 * Created by mohammadrezanajafi on 5/30/17.
 */
class AppConfig : Application() {

  var appComponent: AppComponent by Delegates.notNull<AppComponent>()

  companion object {
    var instance: AppConfig by Delegates.notNull<AppConfig>()
  }


  override fun onCreate() {
    super.onCreate()
    instance = this

    CalligraphyConfig.initDefault(
        CalligraphyConfig.Builder().setFontAttrId(R.attr.fontPath).setDefaultFontPath(
            "fonts/IRANSansMobile.ttf").build())

    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()


    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }

  }


  fun getComponent(fragment: Fragment): FragmentComponent {
    assert(fragment.activity != null)
    val application = fragment.context.applicationContext as AppConfig
    return application.appComponent.plus(ActivityModule(fragment.activity as AppCompatActivity))
        .plus(FragmentModule(fragment))
  }

  fun getComponent(activity: AppCompatActivity): ActivityComponent {
    val application = activity.applicationContext as AppConfig
    return application.appComponent.plus(ActivityModule(activity))
  }


  fun getComponent(): AppComponent? {
    return appComponent
  }
}