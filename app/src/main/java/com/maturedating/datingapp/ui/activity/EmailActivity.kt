package com.maturedating.datingapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.mature.baselib.utils.StatusBarHelper
import com.maturedating.datingapp.Constance
import com.maturedating.datingapp.R
import kotlinx.android.synthetic.main.activity_email.*

class EmailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        StatusBarHelper.translucent(this)

        btn_next.setOnClickListener {
            val email: String = edit_email.text.toString().trim()
            if (!TextUtils.isEmpty(email)) {
                val intent = Intent(this, LookForActivity::class.java)
                startActivity(intent)
                spUtils.putString(Constance.USER_EMAIL, email)
                finish()
            }
        }
    }
}