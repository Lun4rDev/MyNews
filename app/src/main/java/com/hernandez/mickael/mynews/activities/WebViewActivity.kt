package com.hernandez.mickael.mynews.activities

/**
 * Created by Mickael Hernandez on 15/11/2017.
 */

import android.app.Activity;
import android.app.Application
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView;
import android.webkit.WebViewClient
import com.hernandez.mickael.mynews.R

class WebViewActivity : AppCompatActivity() {

	lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_webview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var url : String? = intent.extras.getString("url")
        var title : String? = intent.extras.getString("title")
        if(url != "" && url != null) {
            webView = findViewById(R.id.webView)
            //webView.settings.javaScriptEnabled = true;
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
        this.title = title
	}

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}