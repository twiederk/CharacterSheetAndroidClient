package com.android.ash.charactersheet.gui.main.exportimport;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Displays the characters to export in a list view. Each character is displayed with a checkbox and its name.
 */
public class ExportCharacterAdapter extends DisplayArrayAdapter<Character> {

    private final List<Character> selectedCharacters;

    /**
     * Creates ExportCharacterAdapter used by a list view.
     * 
     * @param context
     *            The context of the activity.
     * @param displayService
     *            The service to display data.
     * @param resourceId
     *            The id of the list item resource file.
     * @param listModel
     *            The characters to display.
     */
    public ExportCharacterAdapter(final Context context, final DisplayService displayService, final int resourceId,
            final ListModel<Character> listModel) {
        super(context, displayService, resourceId, listModel.getItems());
        selectedCharacters = new ArrayList<Character>(listModel.getItems().size());
    }

    @Override
    protected void fillView(final View view, final Character character) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.listitem_export_checkbox);
        checkBox.setText(character.getName());

        checkBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                final CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    selectedCharacters.add(character);
                } else {
                    selectedCharacters.remove(character);
                }
            }
        });
    }

    /**
     * Returns all selected characters.
     * 
     * @return All selected characters.
     */
    public List<Character> getSelectedCharacters() {
        return selectedCharacters;
    }
}
