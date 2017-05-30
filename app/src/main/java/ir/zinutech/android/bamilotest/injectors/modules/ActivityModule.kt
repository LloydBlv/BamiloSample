package ir.zinutech.android.bamilotest.injectors.modules

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides

/**
 * Created by mohammadrezanajafi on 2/28/16.
 */

@Module
class ActivityModule(internal val mActivity: AppCompatActivity) {

  @Provides
  fun activity(): Activity {
    return mActivity
  }

  @Provides
  fun context(): Context {
    return activity()
  }

  @Provides
  internal fun layoutInflater(): LayoutInflater {
    return mActivity.layoutInflater
  }
}
