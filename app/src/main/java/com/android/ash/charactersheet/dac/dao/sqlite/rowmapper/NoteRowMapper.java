package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Note;

/**
 * Maps a data row to a new note instance. The date of the note is stored as text in the database with the format given
 * in CharacterDao.DATE_FORMAT (yyyy-MM-dd HH:mm:ss.SSS).
 */
public class NoteRowMapper extends BaseRowMapper {

    private final DateFormat dateFormat;

    /**
     * Creates a new NoteRowMapper with the given date pattern to convert the String into Date.
     * 
     * @param datePattern
     *            The pattern to convert the Sting into Date.
     */
    public NoteRowMapper(final String datePattern) {
        super();
        dateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, date, text
        final Note note = new Note();
        note.setId(cursor.getInt(0));
        note.setDate(parseDate(cursor.getString(1), dateFormat));
        note.setText(cursor.getString(2));
        return note;
    }

}
