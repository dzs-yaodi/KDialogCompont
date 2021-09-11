package com.afftechlimited.adultdatingapp.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.mature.baselib.utils.StatusBarHelper
import com.afftechlimited.adultdatingapp.R
import kotlinx.android.synthetic.main.activity_register_new.*

class RegisterNewActivity: BaseActivity() {

    private val ageItems = arrayOf("18-25", "26-35", "36-45", "46-55", "55-60", "60+")
    private val genderItems = arrayOf("male", "women")
    private val lookForItems = arrayOf("men", "woman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new)
        StatusBarHelper.translucent(this)

        tv_age.setOnClickListener { showAgeDialogs() }
        tv_gender.setOnClickListener { showGenderDialig() }
        tv_lookfor.setOnClickListener { lookForDialog() }

        btn_next.setOnClickListener {
            val age = tv_age.text.toString()
            val gender = tv_gender.text.toString()
            val lookfor = tv_lookfor.text.toString()
            if(!TextUtils.isEmpty(age) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(lookfor)) {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showAgeDialogs() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(ageItems) { dialog: DialogInterface, which: Int ->
            val items = ageItems[which]
            tv_age.text = items
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun showGenderDialig() {
        val builder =
                AlertDialog.Builder(this)
        builder.setItems(
                genderItems
        ) { dialog: DialogInterface, which: Int ->
            val items = genderItems[which]
            tv_gender.text = items
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun lookForDialog() {
        val builder =
                AlertDialog.Builder(this)
        builder.setItems(
                lookForItems
        ) { dialog: DialogInterface, which: Int ->
            val items = lookForItems[which]
            tv_lookfor.text = items
            dialog.dismiss()
        }
        builder.create().show()
    }
}