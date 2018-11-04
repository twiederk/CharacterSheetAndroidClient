package com.android.ash.charactersheet.gui.main.exportimport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.android.ash.charactersheet.gui.util.LogActivity;

/**
 * The administration contains a button for each administration sub menu.
 */
public class ExportMenuActivity extends LogActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export_menu);
        setTitle(R.string.export_menu_title);
        setButtonsOnClickListeners();

    }

    private void setButtonsOnClickListeners() {
        setButtonOnClickListener(R.id.export_menu_character_button, ExportCharacterActivity.class);
        setButtonOnClickListener(R.id.export_menu_equipment_button, ExportEquipmentActivity.class);
    }

    private void setButtonOnClickListener(final int id, final Class<? extends Activity> activity) {
        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new IntentOnClickListener(new Intent(this, activity)));
    }
}
