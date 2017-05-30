package ir.zinutech.android.bamilotest.injectors.components

import dagger.Subcomponent
import ir.zinutech.android.bamilotest.injectors.modules.FragmentModule
import ir.zinutech.android.bamilotest.injectors.scopes.FragmentScope
import ir.zinutech.android.bamilotest.ui.fragments.CategoryListFragment

/**
 * Created by mohammadrezanajafi on 2/28/16.
 */


@FragmentScope
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
  fun inject(categoryListFragment: CategoryListFragment)
}
