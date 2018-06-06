package cchcc.learn.amu

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cchcc.learn.amu.databinding.ActivityMainBinding
import cchcc.learn.amu.e01.E01Activity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)) {
            host = this@MainActivity
        }
    }

    fun startE01() {
        startActivity(Intent(this, E01Activity::class.java))
    }

}
