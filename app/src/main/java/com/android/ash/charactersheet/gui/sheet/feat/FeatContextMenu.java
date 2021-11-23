package com.android.ash.charactersheet.gui.sheet.feat;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static com.android.ash.charactersheet.gui.sheet.FeatPageFragment.RESULT_CODE_CATEGORY;

import android.content.Intent;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;

/**
 * The context menu of the feat list. Offers add and remove feat and edit of feat category.
 */
public class FeatContextMenu implements OnCreateContextMenuListener, OnMenuItemClickListener {

    private static final int CONTEXT_MENU_ADD_FEAT_TO_CHARACTER = 0;
    private static final int CONTEXT_MENU_REMOVE_FEAT_FROM_CHARACTER = 1;
    private static final int CONTEXT_MENU_EDIT_FEAT = 2;

    private final Fragment fragment;
    private final FeatModel featModel;
    private FeatListItem featListItem;
    private final Resources resources;

    /**
     * Instantiates feat context menu.
     * 
     * @param fragment
     *            The fragment the context menu belongs to.
     * @param featModel
     *            The feat model to determine the menu items of.
     * @param resources
     *            The resources supply the text of the menu items.
     */
    public FeatContextMenu(final Fragment fragment, final FeatModel featModel, final Resources resources) {
        this.fragment = fragment;
        this.featModel = featModel;
        this.resources = resources;
    }

    /**
     * Creates context menu. Add feat is shown if all feats are shown, otherwise remove feat ist shown. Edit category is
     * displayed for feat which are owned by the character and can be gained multiple times.
     */
    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final ListView listView = (ListView) view;
        featListItem = (FeatListItem) listView.getAdapter().getItem(info.position);

        menu.setHeaderTitle(featListItem.getName());
        addMenuItem(menu, getAddRemoveLabel(), getAddRemoveItemId());
        if (featListItem instanceof CharacterFeatItem && featListItem.isMultiple()) {
            addMenuItem(menu, resources.getString(R.string.feat_list_edit_feat), CONTEXT_MENU_EDIT_FEAT);
        }
    }

    private void addMenuItem(final Menu menu, final String label, final int actionId) {
        final MenuItem menuItem = menu.add(0, actionId, 0, label);
        menuItem.setOnMenuItemClickListener(this);
    }

    private int getAddRemoveItemId() {
        if (featListItem instanceof CharacterFeatItem) {
            return CONTEXT_MENU_REMOVE_FEAT_FROM_CHARACTER;
        }
        return CONTEXT_MENU_ADD_FEAT_TO_CHARACTER;
    }

    private String getAddRemoveLabel() {
        if (featListItem instanceof CharacterFeatItem) {
            return resources.getString(R.string.feat_list_remove_feat);
        }
        return resources.getString(R.string.feat_list_add_feat);
    }

    /**
     * Handles selected menu item. This could be add/remove feat or edit category.
     */
    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final int actionId = menuItem.getItemId();
        switch (actionId) {
        case CONTEXT_MENU_ADD_FEAT_TO_CHARACTER:
            Logger.debug("Add feat to character: " + featListItem);
            addFeat();
            break;

        case CONTEXT_MENU_REMOVE_FEAT_FROM_CHARACTER:
            Logger.debug("Remove feat from character: " + featListItem);
            removeFeat();
            break;

        case CONTEXT_MENU_EDIT_FEAT:
            Logger.debug("Edit feat: " + featListItem);
            editFeat();
            break;

        default:
            break;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void editFeat() {
        final Intent intent = new Intent(fragment.getActivity(), FeatEditActivity.class);
        final CharacterFeatItem characterFeatItem = (CharacterFeatItem) featListItem;
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, characterFeatItem.getCharacterFeat());
        fragment.startActivityForResult(intent, RESULT_CODE_CATEGORY);
        featModel.setCharacterFeatItem(characterFeatItem);
    }

    private void addFeat() {
        featModel.addFeat((FeatItem) featListItem);
    }

    private void removeFeat() {
        featModel.removeFeat((CharacterFeatItem) featListItem);
    }
}
