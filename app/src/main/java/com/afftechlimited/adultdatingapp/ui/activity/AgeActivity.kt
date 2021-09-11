package com.afftechlimited.adultdatingapp.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.mature.baselib.utils.StatusBarHelper
import com.afftechlimited.adultdatingapp.Constance
import com.afftechlimited.adultdatingapp.R
import kotlinx.android.synthetic.main.activity_age.*

class AgeActivity: BaseActivity() {

    private val ageItems = arrayOf("18-25", "26-35", "36-45", "46-55", "55-60", "60+")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        StatusBarHelper.translucent(this)

        tv_age.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setItems(ageItems) { dialog: DialogInterface, which: Int ->
                val items = ageItems[which]
                tv_age.text = items
                dialog.dismiss()
            }
            builder.create().show()
        }

        btn_next.setOnClickListener {
            val age: String = tv_age.text.toString().trim()
            if (!TextUtils.isEmpty(age)) {
                val intent = Intent(this, GenderActivity::class.java)
                startActivity(intent)
                spUtils.putString(Constance.USER_AGE, age)
                finish()
            }
        }
    }
}