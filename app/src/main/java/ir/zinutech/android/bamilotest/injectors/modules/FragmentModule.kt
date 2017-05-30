package ir.zinutech.android.bamilotest.injectors.modules

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides

/**
 * Created by mohammadrezanajafi on 2/28/16.
 */

@Module
class FragmentModule(internal val mFragment: Fragment) {

  @Provides
  fun context(): Context {
    return mFragment.context
  }

  @Provides
  fun provideFragmentManager(): FragmentManager {
    return mFragment.fragmentManager
  }
}
