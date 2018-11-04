package com.android.ash.charactersheet.gui.sheet.note;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.content.Intent;
import android.os.Bundle;

import com.d20charactersheet.framework.boc.model.Note;

/**
 * Form to edit an existing note. The date and text of the note is displayed. The note is delivered in the extras of the
 * intent.
 */
public class NoteEditActivity extends NoteActivity {

    @Override
    protected Note createForm() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final Note note = (Note) extras.getSerializable(INTENT_EXTRA_DATA_OBJECT);
        return note;
    }

    @Override
    protected void saveForm() {
        characterService.updateNote(note, character);
    }

}
