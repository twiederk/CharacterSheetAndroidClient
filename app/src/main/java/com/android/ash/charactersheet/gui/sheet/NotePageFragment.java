package com.android.ash.charactersheet.gui.sheet;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.note.NoteArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.note.NoteCreateActivity;
import com.android.ash.charactersheet.gui.sheet.note.NoteEditActivity;
import com.d20charactersheet.framework.boc.model.Note;
import com.d20charactersheet.framework.boc.util.NoteComparator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.Serializable;
import java.util.List;

/**
 * Displays the notes of the character as a list. The notes are displayed with date and text. The option menu offers the
 * option to create a new note. Single touch on a note opens it for edit. Long touch on a note opens the context menu
 * with the option to delete the note.
 */
public class NotePageFragment extends PageFragment implements OnItemClickListener {

    private static final int CONTEXT_MENU_NOTE_DELETE = 100;

    private ArrayAdapter<Note> adapter;
    private ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.page_note;
    }

    @Override
    protected void doCreateView() {
        setFavoriteActionButton();
        listView = view.findViewById(R.id.note_list_view);
    }

    private void setFavoriteActionButton() {
        final FloatingActionButton favoriteActionButton = view.findViewById(R.id.favorite_action_button);
        favoriteActionButton.setOnClickListener(view -> startActivity(new Intent(getActivity(), NoteCreateActivity.class)));
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.NOTE);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "NotePageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        adapter = createAdapter();
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
        listView.setOnItemClickListener(this);
    }

    private ArrayAdapter<Note> createAdapter() {
        final List<Note> notes = character.getNotes();
        final ArrayAdapter<Note> adapter = new NoteArrayAdapter(getActivity(), displayService, notes);
        adapter.sort(new NoteComparator());
        return adapter;
    }

    private void edit(final Serializable item) {
        final Intent intent = new Intent(getActivity(), NoteEditActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, item);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(@NonNull final ContextMenu menu, @NonNull final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final Note note = (Note) listView.getAdapter().getItem(info.position);
        menu.setHeaderTitle(displayService.getDisplayNoteFirstLine(note));
        menu.add(0, CONTEXT_MENU_NOTE_DELETE, 0, R.string.note_list_context_menu_delete_note);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem menuItem) {
        final Note note = getNote(menuItem);
        if (menuItem.getItemId() == CONTEXT_MENU_NOTE_DELETE) {
            return deleteNote(note);
        }
        return super.onContextItemSelected(menuItem);
    }

    private Note getNote(final MenuItem menuItem) {
        final AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
        return (Note) listView.getAdapter().getItem(menuInfo.position);
    }

    @SuppressWarnings("SameReturnValue")
    private boolean deleteNote(final Note note) {
        characterService.deleteNote(note, character);
        adapter.remove(note);
        listView.clearTextFilter();
        return true;
    }

    @Override
    public void onItemClick(final AdapterView<?> adapter, final View view, final int position, final long id) {
        final Serializable item = (Serializable) listView.getItemAtPosition(position);
        edit(item);
    }

}
