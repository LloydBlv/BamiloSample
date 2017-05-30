package ir.zinutech.android.bamilotest.injectors.components

import dagger.Component
import ir.zinutech.android.bamilotest.injectors.modules.ActivityModule
import ir.zinutech.android.bamilotest.injectors.modules.AppModule
import javax.inject.Singleton

/**
 * Created by mohammadrezanajafi on 2/28/16.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
  operator fun plus(module: ActivityModule): ActivityComponent

}
