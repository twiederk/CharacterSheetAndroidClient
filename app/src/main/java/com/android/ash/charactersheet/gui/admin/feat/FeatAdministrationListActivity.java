package com.android.ash.charactersheet.gui.admin.feat;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.util.FeatComparator;

import java.util.List;

/**
 * Displays all feats. The option menu allows to call the create feat activity. The context menu of each feat allows to
 * edit or delete it.
 */
public class FeatAdministrationListActivity extends AdministrationListActivity<Feat> {

    private static final int CONTEXT_MENU_FEAT_DELETE = 100;

    /**
     * Contains options to edit and delete a feat.
     * 
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
     *      android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final Feat feat = (Feat) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(feat.getName());
        menu.add(0, CONTEXT_MENU_FEAT_DELETE, 0, R.string.feat_administration_list_context_menu_delete_feat);
    }

    /**
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(final MenuItem menuItem) {

        if (menuItem.getItemId() == CONTEXT_MENU_FEAT_DELETE) {
            final Feat feat = getFeat(menuItem);
            return deleteFeat(feat);
        }
        return super.onContextItemSelected(menuItem);
    }

    private Feat getFeat(final MenuItem menuItem) {
        final AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
        return (Feat) getListView().getAdapter().getItem(menuInfo.position);
    }

    @SuppressWarnings({"SameReturnValue", "unchecked"})
    private boolean deleteFeat(final Feat feat) {
        gameSystem.deleteFeat(feat);
        final ArrayAdapter<Feat> adapter = (ArrayAdapter<Feat>) getListView().getAdapter();
        adapter.remove(feat);
        getListView().clearTextFilter();
        return true;
    }

    @Override
    protected int getTitleResource() {
        return R.string.feat_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Feat> getAdapter() {
        final List<Feat> allFeats = gameSystem.getAllFeats();
        final ArrayAdapter<Feat> adapter = new NameDisplayArrayAdapter<>(this, displayService, allFeats);
        adapter.sort(new FeatComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, FeatAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Feat feat) {
        return new Intent(this, FeatAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT, feat.getId());
    }

}
