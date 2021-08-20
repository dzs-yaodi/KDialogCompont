package com.maturedating.datingapp.ui.activity

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mature.baselib.utils.SharePerferenceUtils
import com.tbruyelle.rxpermissions2.RxPermissions

open class BaseActivity: AppCompatActivity() {

    private val rxPermissions by  lazy { RxPermissions(this) }
    protected val spUtils by lazy { SharePerferenceUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val d = rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { granted: Boolean? ->
                    if (!granted!!) {
                        Toast.makeText(this, "Please enable the necessary permissions", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}