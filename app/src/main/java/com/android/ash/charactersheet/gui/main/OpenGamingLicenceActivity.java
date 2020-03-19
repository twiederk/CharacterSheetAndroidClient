package com.android.ash.charactersheet.gui.main;

import android.os.Bundle;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

/**
 * Activity to display open gaming licence.
 */
public class OpenGamingLicenceActivity extends LogAppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_gaming_licence);
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.ogl_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


}
