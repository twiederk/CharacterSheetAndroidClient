package com.android.ash.charactersheet.gui.sheet.note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Note;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Displays a note in a list of items. The create date and the first line of the note is displayed.
 */
public class NoteArrayAdapter extends DisplayArrayAdapter<Note> {

    /**
     * Creates Adapter to display notes in a list.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            The service used to display the note in humanreadable format.
     * @param notes
     *            The notes to display.
     */
    public NoteArrayAdapter(final Context context, final DisplayService displayService, final List<Note> notes) {
        super(context, displayService, R.layout.listitem_note, notes);
    }

    @Override
    protected void fillView(final View view, final Note note) {
        final TextView dateTextView = (TextView) view.findViewById(R.id.note_date);
        dateTextView.setText(getDate(note));

        final TextView textTextView = (TextView) view.findViewById(R.id.note_text);
        textTextView.setText(displayService.getDisplayNoteFirstLine(note));
    }

    private String getDate(final Note note) {
        final DateFormat dateFormat = new SimpleDateFormat(getContext().getResources().getString(
                R.string.date_and_time_format), Locale.getDefault());
        final String date = dateFormat.format(note.getDate());
        return date;
    }
}
