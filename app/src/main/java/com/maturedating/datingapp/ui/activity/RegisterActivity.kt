package com.maturedating.datingapp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.mature.baselib.utils.StatusBarHelper
import com.maturedating.datingapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        StatusBarHelper.translucent(this)

        progressbar.postDelayed({
            val intent = Intent(this, PromptActivity::class.java)
            startActivity(intent)
        },2000)
    }
}