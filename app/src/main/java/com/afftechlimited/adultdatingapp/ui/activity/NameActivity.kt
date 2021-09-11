package com.afftechlimited.adultdatingapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.mature.baselib.utils.StatusBarHelper
import com.afftechlimited.adultdatingapp.Constance
import com.afftechlimited.adultdatingapp.R
import kotlinx.android.synthetic.main.activity_username.*

class NameActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)

        StatusBarHelper.translucent(this)
        btn_continue.setOnClickListener {
            val name = edit_user.text.toString().trim()
            if (!TextUtils.isDigitsOnly(name)) {
                val intent = Intent(this,PromptActivity::class.java)
                    .apply {
                        putExtra("name",name)
                    }
                startActivity(intent)

                spUtils.putString(Constance.USER_NAME,name)
                finish()
            }
        }
    }
}