package com.android.ash.charactersheet.gui.sheet.note;

import android.view.KeyEvent;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.BaseCharacterSheetActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.util.MessageManager;
import com.d20charactersheet.framework.boc.model.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Base activity to create or edit a note. Displays character name as title and the character image in background. The
 * create date and text of the note is also displayed, only the text is editable.
 */
public abstract class NoteActivity extends BaseCharacterSheetActivity {

    Note note;

    private MessageManager messageManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    protected void doCreate() {
        messageManager = new MessageManager(this, displayService);

        note = createForm();
        setNoteDate();
        setNoteText();
    }

    private void setNoteDate() {
        final String format = getResources().getString(R.string.date_and_time_format);
        final DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        final String noteDate = dateFormat.format(note.getDate());
        final TextView noteDateTextView = findViewById(R.id.note_date);
        noteDateTextView.setText(noteDate);
    }

    private void setNoteText() {
        final TextView noteTextView = findViewById(R.id.note_text);
        noteTextView.setText(note.getText());
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            try {
                fillForm();
                saveForm();
                finish();
            } catch (final Exception exception) {
                Logger.error("Can't store data", exception);
                messageManager.showErrorMessage(exception);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void fillForm() {
        final TextView noteTextView = findViewById(R.id.note_text);
        note.setText(noteTextView.getText().toString());
    }

    protected abstract Note createForm();

    protected abstract void saveForm();
}
