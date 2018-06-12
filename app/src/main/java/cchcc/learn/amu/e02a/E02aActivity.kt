package cchcc.learn.amu.e02a

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R

class E02aActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e02a)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, E02aFragment.newInstance())
                .commit()
    }
}
