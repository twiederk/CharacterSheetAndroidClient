package com.android.ash.charactersheet.gui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.IntentOnClickListener
import com.android.ash.charactersheet.gui.util.LogActivity

/**
 * Displays the about view of the application. Containing the icon, the version of the application and a link to the
 * home page of the application.
 */
class AboutActivity : LogActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle(R.string.about_title)
        setVersionName()
        setOnClickListener()
    }

    private fun setVersionName() {
        val versionNameTextView = findViewById<TextView>(R.id.app_version)
        versionNameTextView.text = BuildConfig.VERSION_NAME
    }

    private fun setOnClickListener() {
        val oglButton = findViewById<View>(R.id.about_button_ogl)
        oglButton.setOnClickListener(IntentOnClickListener(Intent(this, OpenGamingLicenceActivity::class.java)))
    }
}