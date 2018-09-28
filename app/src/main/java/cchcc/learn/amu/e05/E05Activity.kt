package cchcc.learn.amu.e05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R
import cchcc.learn.amu.e02.E02Fragment

class E05Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e05)

        val e02Fragment = E02Fragment.newInstance()
        val e05Fragment = E05Fragment.newInstance { e02Fragment.viewModel }

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_top, e02Fragment)
                .replace(R.id.fl_bottom, e05Fragment)
                .commit()
    }
}
