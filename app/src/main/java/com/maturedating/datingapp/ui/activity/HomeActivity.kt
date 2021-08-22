package com.maturedating.datingapp.ui.activity

import android.os.Bundle
import com.mature.baselib.utils.UITools
import com.maturedating.datingapp.R
import com.maturedating.datingapp.ui.fragment.MessageFragment
import com.maturedating.datingapp.ui.fragment.UsersFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: BaseActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        UITools.initTitleColorBar(this,findViewById(R.id.frame_contailer),"#404873")

        tv_user.setOnClickListener {
            if (index == 1) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, UsersFragment()).commit()
                index = 0
            }
        }

        tv_message.setOnClickListener {
            if (index == 0) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, MessageFragment()).commit()
                index = 1
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, UsersFragment()).commit()
    }
}