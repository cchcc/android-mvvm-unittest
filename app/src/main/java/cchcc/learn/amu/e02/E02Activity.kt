package cchcc.learn.amu.e02

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R

class E02Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e02)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, E02Fragment.newInstance())
                .commit()
    }
}
