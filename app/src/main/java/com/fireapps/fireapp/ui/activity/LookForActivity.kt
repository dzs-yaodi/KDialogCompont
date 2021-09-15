package com.fireapps.fireapp.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.fireapps.fireapp.Constance
import com.fireapps.fireapp.R
import kotlinx.android.synthetic.main.activity_look_for.*

class LookForActivity: BaseActivity() {

    private val ageItems = arrayOf("men", "woman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_for)

        tv_lookfor.setOnClickListener {
            val builder =
                AlertDialog.Builder(this)
            builder.setItems(
                ageItems
            ) { dialog: DialogInterface, which: Int ->
                val items = ageItems[which]
                tv_lookfor.text = items
                dialog.dismiss()
            }
            builder.create().show()
        }

        btn_next.setOnClickListener {
            val lookfor: String = tv_lookfor.text.toString().trim()
            if (!TextUtils.isEmpty(lookfor)) {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                spUtils.putString(Constance.USER_LOOKING_FOR, lookfor)
                finish()
            }
        }
    }
}