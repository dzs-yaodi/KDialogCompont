package com.afftechlimited.adultdatingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afftechlimited.adultdatingapp.R
import kotlinx.android.synthetic.main.fragment_list_layout.*

class MessageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = "List of your messages:"

        progressbar.postDelayed({
            tv_content?.text = "You do not have any messages yet"
            progressbar?.visibility = View.GONE
        }, 300)
    }
}