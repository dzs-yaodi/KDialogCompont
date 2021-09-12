package com.afftechlimited.adultdatingapp.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mature.baselib.utils.SharePerferenceUtils

open class BaseActivity: AppCompatActivity() {

    private val permission = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    protected val spUtils by lazy { SharePerferenceUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setPermissiont() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val checkCallPhonePermission0 = ContextCompat.checkSelfPermission(this, permission[0])
            val checkCallPhonePermission1 = ContextCompat.checkSelfPermission(this, permission[1])
            val checkCallPhonePermission3 = ContextCompat.checkSelfPermission(this, permission[2])
            if (checkCallPhonePermission0 != PackageManager.PERMISSION_GRANTED || checkCallPhonePermission1 != PackageManager.PERMISSION_GRANTED || checkCallPhonePermission3 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
                    1024
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1024) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            } else {
                // Permission Denied
                Toast.makeText(this, "Please enable the necessary permissions", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}