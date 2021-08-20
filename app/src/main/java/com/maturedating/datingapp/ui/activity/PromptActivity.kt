package com.maturedating.datingapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.maturedating.datingapp.Constance
import com.maturedating.datingapp.R
import kotlinx.android.synthetic.main.activity_prompt.*

class PromptActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        val name = intent.getStringExtra("name")
        if (!TextUtils.isEmpty(name)) {
            tv_name.text = "Hello $name"
        } else {
            tv_name.text = resources.getString(R.string.app_dialog_logo_name)
            val userName = spUtils.getString(Constance.USER_NAME)
            tv_content.text = userName + "thanks for register!"
        }

        btn_continue.setOnClickListener {
            if (!TextUtils.isEmpty(name)) {
                startActivity(Intent(this, AgeActivity::class.java))
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            finish()
        }
    }
}