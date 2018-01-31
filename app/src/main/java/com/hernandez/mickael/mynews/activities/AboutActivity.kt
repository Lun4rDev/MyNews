package com.hernandez.mickael.mynews.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hernandez.mickael.mynews.R
import kotlinx.android.synthetic.main.activity_about.*
import android.content.Intent
import android.net.Uri


/**
 * Created by Mickael Hernandez on 10/01/2018.
 */
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        github_btn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(getString(R.string.github_link))
            startActivity(intent)
        }
    }
}