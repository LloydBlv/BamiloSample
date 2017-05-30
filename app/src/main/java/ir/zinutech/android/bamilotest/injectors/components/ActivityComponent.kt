package ir.zinutech.android.bamilotest.injectors.components

import dagger.Subcomponent
import ir.zinutech.android.bamilotest.injectors.modules.ActivityModule
import ir.zinutech.android.bamilotest.injectors.modules.FragmentModule
import ir.zinutech.android.bamilotest.injectors.scopes.ActivityScope
import ir.zinutech.android.bamilotest.ui.activities.MainActivity
import ir.zinutech.android.bamilotest.ui.activities.ProductDetailsActivity

/**
 * Created by mohammadrezanajafi on 2/28/16.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
  fun inject(mainActivity: MainActivity)
  fun inject(productDetailsActivity: ProductDetailsActivity)
  operator fun plus(module: FragmentModule): FragmentComponent

}
