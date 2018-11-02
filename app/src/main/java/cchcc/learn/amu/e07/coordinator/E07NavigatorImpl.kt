package cchcc.learn.amu.e07.coordinator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cchcc.learn.amu.e07.E07WordActivity

class E07NavigatorImpl(val activity: AppCompatActivity) : E07Navigator {

    override fun <T> navigateWord(args: Map<String, T>) {
        val extras = args["extras"] as Bundle
        val bundle = args["bundle"] as Bundle

        activity.startActivity(Intent(activity, E07WordActivity::class.java)
                .putExtras(extras)
                , bundle)
    }

    override fun navigateBackToWordList() {
        activity.onBackPressed()
    }
}