package ir.zinutech.android.bamilotest.ui.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ir.zinutech.android.bamilotest.R
import ir.zinutech.android.bamilotest.R.layout
import ir.zinutech.android.bamilotest.ui.fragments.CategoryListFragment
import kotlinx.android.synthetic.main.activity_main.activity_fragment_base_toolbar
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.activity_fragment_base_frame_id, CategoryListFragment.newInstance("men_shirts"))
          .commit()

    }

    setSupportActionBar(activity_fragment_base_toolbar)
  }
}
