package com.hernandez.mickael.mynews.activities

/**
 * Created by Mickael Hernandez on 15/11/2017.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hernandez.mickael.mynews.R

/** Activity displaying the web page of an article */
class WebViewActivity : AppCompatActivity() {

    /** WebView view */
	lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_webview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get url and title of the article from the intent
        val url : String? = intent.extras.getString("url")
        val title : String? = intent.extras.getString("title")

        // Set activity title to the article title
        this.title = title

        if(url != "" && url != null) {
            webView = findViewById(R.id.webView)
            //webView.settings.javaScriptEnabled = true;
            webView.webViewClient = WebViewClient()
            // loads the article url into the webview
            webView.loadUrl(url)
        } else {
            finish()
        }
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