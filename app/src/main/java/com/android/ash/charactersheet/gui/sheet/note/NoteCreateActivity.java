package com.android.ash.charactersheet.gui.sheet.note;

import com.d20charactersheet.framework.boc.model.Note;

import java.util.Date;

/**
 * Displays a form to create a new note. The note is initialized with the current date and an empty text.
 */
public class NoteCreateActivity extends NoteActivity {

    @Override
    protected Note createForm() {
        final Note note = new Note();
        note.setDate(new Date());
        note.setText("");
        return note;
    }

    @Override
    protected void saveForm() {
        characterService.createNote(note, character);
    }

}
