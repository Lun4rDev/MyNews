package com.hernandez.mickael.mynews.activities

/**
 * Created by Mickael Hernandez on 15/11/2017.
 */

import android.app.Activity;
import android.app.Application
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView;
import android.webkit.WebViewClient
import com.hernandez.mickael.mynews.R

class WebViewActivity : AppCompatActivity() {

	lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_webview)
        var url : String? = intent.extras.getString("url")
        if(url != "" && url != null) {
            webView = findViewById(R.id.webView)
            //webView.settings.javaScriptEnabled = true;
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
        title = url
	}

}