package com.dragon.wallpaperapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.dragon.wallpaperapp.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * Created by D22434 on 2017/12/25.
 */
class WebViewActivity : AppCompatActivity() {
    companion object {
        var WEB_URL: String = ""
        var TAG: String = "WebViewActivity"
    }

    var url: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        url = intent.getStringExtra(WEB_URL)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        Logger.e("url=" + url)
        webView.loadUrl(url)
        //启用支持javascript
        webView.settings.javaScriptEnabled = true
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        loading_progress.max = 100
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                loading_progress.progress = newProgress
                if (newProgress == 100) {
                    loading_progress.visibility = View.GONE
                }
            }
        }
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.loadsImagesAutomatically = true//支持自动加载图片
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

    }

    //改写物理按键——返回的逻辑
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack()//返回上一页面
                return true
            } else {
                onBackPressed()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}