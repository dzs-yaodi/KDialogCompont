package com.fireapps.fireapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fireapps.fireapp.R
import kotlinx.android.synthetic.main.fragment_list_layout.*

class UsersFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = "This users are online now:"

        progressbar.postDelayed({
            tv_content?.text = "No users online"
            progressbar?.visibility = View.GONE
        }, 300)
    }
}