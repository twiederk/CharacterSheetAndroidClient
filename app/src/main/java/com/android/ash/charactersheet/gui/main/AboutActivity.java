package com.android.ash.charactersheet.gui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.android.ash.charactersheet.gui.util.LogActivity;

/**
 * Displays the about view of the application. Containing the icon, the version of the application and a link to the
 * home page of the application.
 */
public class AboutActivity extends LogActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(R.string.about_title);
        setVersionName();
        setOnClickListener();
    }

    private void setVersionName() {
        final TextView versionNameTextView = findViewById(R.id.app_version);
        versionNameTextView.setText(getString(R.string.app_version_name));
    }

    private void setOnClickListener() {
        final View oglButton = findViewById(R.id.about_button_ogl);
        oglButton.setOnClickListener(new IntentOnClickListener(new Intent(this, OpenGamingLicenceActivity.class)));
    }

}
