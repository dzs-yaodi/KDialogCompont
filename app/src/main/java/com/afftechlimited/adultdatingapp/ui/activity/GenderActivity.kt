package com.afftechlimited.adultdatingapp.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.mature.baselib.utils.StatusBarHelper
import com.afftechlimited.adultdatingapp.Constance
import com.afftechlimited.adultdatingapp.R
import kotlinx.android.synthetic.main.activity_gender.*

class GenderActivity: BaseActivity() {

    private val ageItems = arrayOf("male", "women")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)
        StatusBarHelper.translucent(this)

        tv_gender.setOnClickListener {
            val builder =
                AlertDialog.Builder(this)
            builder.setItems(
                ageItems
            ) { dialog: DialogInterface, which: Int ->
                val items = ageItems[which]
                tv_gender.text = items
                dialog.dismiss()
            }
            builder.create().show()
        }

        btn_next.setOnClickListener {
            val gender: String = tv_gender.text.toString()
            if (!TextUtils.isEmpty(gender)) {
                val intent = Intent(this, EmailActivity::class.java)
                startActivity(intent)
                spUtils.putString(Constance.USER_GENDER, gender)
                finish()
            }
        }
    }
}