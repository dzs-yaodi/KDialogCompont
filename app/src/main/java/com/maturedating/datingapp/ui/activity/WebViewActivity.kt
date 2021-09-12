package com.maturedating.datingapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.Toast
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebViewClient
import com.maturedating.datingapp.MediaUtility
import com.maturedating.datingapp.OpenFileWebChromeClient
import com.maturedating.datingapp.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.File

class WebViewActivity : BaseActivity() {

    private var mAgentWeb: AgentWeb? = null
    private val mOpenFileWebChromeClient: OpenFileWebChromeClient by lazy {
        OpenFileWebChromeClient(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setPermission()
        val loadUrl = intent.getStringExtra("load_url")
        if (TextUtils.isEmpty(loadUrl)) {
            Toast.makeText(this, "load_url is null", Toast.LENGTH_SHORT).show()
            finish()
        }

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                frameLayout_web,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            .useDefaultIndicator()
            .setWebViewClient(webViewClient)
            .setWebChromeClient(mOpenFileWebChromeClient)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW) //打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl()
            .createAgentWeb()
            .ready()
            .go(loadUrl)

        val webSettings = mAgentWeb?.agentWebSettings?.webSettings
        webSettings?.let {
            it.loadWithOverviewMode = true
            //下面这些
            it.setSupportZoom(true) //设置可以支持缩放

            it.builtInZoomControls = true //设置出现缩放工具

            it.useWideViewPort = true //扩大比例的缩放

            it.displayZoomControls = false //隐藏缩放控件
            it.allowFileAccess = true
            it.allowContentAccess = true

            it.textZoom = 100
            it.javaScriptEnabled = true
        }

    }

    private val webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    override fun onPause() {
        mAgentWeb!!.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb!!.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb!!.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OpenFileWebChromeClient.REQUEST_FILE_PICKER) {
            if (mOpenFileWebChromeClient.mFilePathCallback != null) {
                val result = if (data == null || resultCode != RESULT_OK) null else data.data
                if (result != null) {
                    val path: String = MediaUtility.getPath(
                        applicationContext,
                        result
                    )
                    val uri = Uri.fromFile(File(path))
                    mOpenFileWebChromeClient.mFilePathCallback
                        .onReceiveValue(uri)
                } else {
                    mOpenFileWebChromeClient.mFilePathCallback
                        .onReceiveValue(null)
                }
            }
            if (mOpenFileWebChromeClient.mFilePathCallbacks != null) {
                val result = if (data == null || resultCode != RESULT_OK) null else data.data
                if (result != null) {
                    val path: String = MediaUtility.getPath(
                        applicationContext,
                        result
                    )
                    val uri = Uri.fromFile(File(path))
                    mOpenFileWebChromeClient.mFilePathCallbacks
                        .onReceiveValue(arrayOf(uri))
                } else {
                    mOpenFileWebChromeClient.mFilePathCallbacks
                        .onReceiveValue(null)
                }
            }
            mOpenFileWebChromeClient.mFilePathCallback = null
            mOpenFileWebChromeClient.mFilePathCallbacks = null
        }
    }
}