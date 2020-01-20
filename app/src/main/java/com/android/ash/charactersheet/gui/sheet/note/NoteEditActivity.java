package com.android.ash.charactersheet.gui.sheet.note;

import android.content.Intent;
import android.os.Bundle;

import com.d20charactersheet.framework.boc.model.Note;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Form to edit an existing note. The date and text of the note is displayed. The note is delivered in the extras of the
 * intent.
 */
public class NoteEditActivity extends NoteActivity {

    @Override
    protected Note createForm() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        return (Note) Objects.requireNonNull(extras).getSerializable(INTENT_EXTRA_DATA_OBJECT);
    }

    @Override
    protected void saveForm() {
        characterService.updateNote(note, character);
    }

}
