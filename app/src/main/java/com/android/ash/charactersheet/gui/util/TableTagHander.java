package com.android.ash.charactersheet.gui.util;

import org.xml.sax.XMLReader;

import android.text.Editable;
import android.text.Html.TagHandler;

/**
 * Converts HTML table tags to displayable letters.
 */
public class TableTagHander implements TagHandler {

    private static final String TAG_TABLE = "table";
    private static final String TAG_TH = "th";
    private static final String TAG_TR = "tr";
    private static final String TAG_TD = "td";

    private static final String TEXT_TABLE_BEGIN = "\n";
    private static final String TEXT_TABLE_END = "\n";

    private static final String TEXT_TABLE_ROW_BEGIN = "";
    private static final String TEXT_TABLE_ROW_END = "|\n";

    private static final String TEXT_TABLE_DATA_BEGIN = " | ";

    @Override
    public void handleTag(final boolean opening, final String tag, final Editable output, final XMLReader xmlReader) {
        if (tag.equalsIgnoreCase(TAG_TABLE)) {
            handleTagTable(opening, output);
        } else if (tag.equalsIgnoreCase(TAG_TH)) {
            handleTagTh(opening, output);
        } else if (tag.equalsIgnoreCase(TAG_TR)) {
            handleTagTr(opening, output);
        } else if (tag.equalsIgnoreCase(TAG_TD)) {
            handleTagTd(opening, output);
        }
    }

    private void handleTagTable(final boolean opening, final Editable output) {
        if (opening) {
            output.append(TEXT_TABLE_BEGIN);
        } else {
            output.append(TEXT_TABLE_END);
        }
    }

    private void handleTagTh(final boolean opening, final Editable output) {
        handleTagTd(opening, output);
    }

    private void handleTagTr(final boolean opening, final Editable output) {
        if (opening) {
            output.append(TEXT_TABLE_ROW_BEGIN);
        } else {
            output.append(TEXT_TABLE_ROW_END);
        }
    }

    private void handleTagTd(final boolean opening, final Editable output) {
        if (opening) {
            output.append(TEXT_TABLE_DATA_BEGIN);
        }
    }

}
