package cchcc.learn.amu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.v4.app.Fragment
import android.widget.FrameLayout

@RestrictTo(RestrictTo.Scope.TESTS)
class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = FrameLayout(this).apply {
            id = R.id.fl_main
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                    , FrameLayout.LayoutParams.MATCH_PARENT)
        }

        setContentView(v)
    }

    inline fun <reified T: Fragment>replaceFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, f, tag)
                .commit()
    }

    inline fun <reified T: Fragment>addFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_main, f, tag)
                .commit()
    }
}
