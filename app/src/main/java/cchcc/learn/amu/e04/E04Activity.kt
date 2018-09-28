package cchcc.learn.amu.e04

import android.Manifest
import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE04Binding
import cchcc.learn.amu.util.activityResult
import cchcc.learn.amu.util.permissionOf
import cchcc.learn.amu.util.requestPermissionResult
import cchcc.learn.amu.util.startActivityWithResult

class E04Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(E04ViewModel::class.java)

        DataBindingUtil.setContentView<ActivityE04Binding>(this, R.layout.activity_e04).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        viewModel.pickContactAction.observe(this, Observer {
            permissionOf(Manifest.permission.READ_CONTACTS).checkOrRequest permissionIsGranted@{

                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                startActivityWithResult(intent) { resultCode, data ->
                    if (resultCode == Activity.RESULT_OK) {

                        val cursor = contentResolver.query(data!!.data, null, null, null, null)
                        val nameAndPhone = cursor.use {
                            it.moveToFirst()

                            val name = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                            val phone = it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA))

                            name to phone
                        }

                        viewModel.nameAndPhone.value = nameAndPhone
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        requestPermissionResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResult(requestCode, resultCode, data)
    }
}
