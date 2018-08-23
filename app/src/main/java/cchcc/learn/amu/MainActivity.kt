package cchcc.learn.amu

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import cchcc.learn.amu.databinding.ActivityMainBinding
import cchcc.learn.amu.e01.E01Activity
import cchcc.learn.amu.e01a.E01aActivity
import cchcc.learn.amu.e02.E02Activity
import cchcc.learn.amu.e02a.E02aActivity
import cchcc.learn.amu.e03.E03Activity
import cchcc.learn.amu.e04.E04Activity
import cchcc.learn.amu.e05.E05Activity
import cchcc.learn.amu.e06.E06Activity
import cchcc.learn.amu.e07.E07WordListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.host = this
        }
    }

    fun startE01() {
        startActivity(Intent(this, E01Activity::class.java))
    }

    fun startE01a() {
        startActivity(Intent(this, E01aActivity::class.java))
    }

    fun startE02() {
        startActivity(Intent(this, E02Activity::class.java))
    }

    fun startE02a() {
        startActivity(Intent(this, E02aActivity::class.java))
    }

    fun startE03() {
        startActivity(Intent(this, E03Activity::class.java))
    }

    fun startE04() {
        startActivity(Intent(this, E04Activity::class.java))
    }

    fun startE05() {
        startActivity(Intent(this, E05Activity::class.java))
    }

    fun startE06() {
        startActivity(Intent(this, E06Activity::class.java))
    }

    fun startE07() {
        startActivity(Intent(this, E07WordListActivity::class.java)
            ,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }

}
