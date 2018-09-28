package cchcc.learn.amu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
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

    inline fun <reified T: androidx.fragment.app.Fragment>replaceFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, f, tag)
                .commit()
    }

    inline fun <reified T: androidx.fragment.app.Fragment>addFragment(f: T, tag: String = T::class.java.name) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_main, f, tag)
                .commit()
    }
}
