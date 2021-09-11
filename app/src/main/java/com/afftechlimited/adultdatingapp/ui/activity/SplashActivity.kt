package com.afftechlimited.adultdatingapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mature.baselib.bean.Config
import com.mature.baselib.http.Api
import com.mature.baselib.http.Disposables
import com.mature.baselib.utils.StatusBarHelper
import com.afftechlimited.adultdatingapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity() {

    private val disposables by lazy { Disposables() }
    private var config: Config? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        StatusBarHelper.translucent(this)

        loadData()

        countDown.setCountDownListener {
            goto()
        }

        countDown.setOnClickListener {
            countDown.stop()
            goto()
        }

        tv_no.setOnClickListener { linear_upgrade.visibility = View.GONE }

        tv_yes.setOnClickListener {
            val uri = Uri.parse(config?.updateUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun goto() {

        config?.let {
            countDown.visibility = View.GONE
            when (it.bug) {
                1 -> {
                    val intent = Intent()
                    intent.setClass(this,NameActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent()
                    intent.setClass(this, WebViewActivity::class.java)
                    intent.putExtra("load_url", config?.webUrl)
                    startActivity(intent)
                }
                else -> {
                    linear_upgrade.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadData() {
        val d = Api.getInstance().apiService
                .config
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    it?.let {
                        config = it
                        startShow()
                    }
                },{
                    it.printStackTrace()
                })
        disposables.add(d)
    }

    private fun startShow() {
        countDown.visibility = View.VISIBLE
        countDown.start()

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}