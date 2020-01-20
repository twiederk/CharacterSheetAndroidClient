package com.android.ash.charactersheet.gui.sheet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.feat.CheckBoxFeatModelController;
import com.android.ash.charactersheet.gui.sheet.feat.FeatContextMenu;
import com.android.ash.charactersheet.gui.sheet.feat.FeatListAdapter;
import com.android.ash.charactersheet.gui.sheet.feat.FeatListOnTabChangeListener;
import com.android.ash.charactersheet.gui.sheet.feat.FeatModel;
import com.android.ash.charactersheet.gui.util.ExpandOnClickListener;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.FeatType;

import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * List of feats. The feats are listed in three tabs. One tab for genereal feats, one for item creation and one for
 * meatmagic. A checkbox is used to show all feats or only the feats of the character. The number of feat of the
 * character is displayed. Single click on a feat expands it view to show detail information. Long touch calls the
 * context menu, which offers options to add, remove or edit a feat.
 */
public class FeatPageFragment extends PageFragment implements Observer {

    /** Intent result code of edit feat category */
    public static final int RESULT_CODE_CATEGORY = 100;

    private static final String SPACE = " ";

    private FeatModel featModel;
    private FeatListOnTabChangeListener featListOnTabChangeListener;

    @Override
    protected int getLayoutId() {
        return R.layout.page_feat;
    }

    @Override
    protected void doCreateView() {

        featModel = new FeatModel(featService.getAllFeats(), character.getCharacterFeats());
        featModel.addObserver(this);

        setHeader();
        setTabs();
        setFeatListView(FeatType.GENERAL, R.id.feat_list_general);
        setFeatListView(FeatType.ITEM_CREATION, R.id.feat_list_item_creation);
        setFeatListView(FeatType.METAMAGIC, R.id.feat_list_metamagic);
        setFeatListView(FeatType.TEAMWORK, R.id.feat_list_teamwork);
    }

    private void setHeader() {
        setNumberOfFeats();
    }

    private void setNumberOfFeats() {
        final TextView freeFeatsTextView = view.findViewById(R.id.feat_list_number_of_feats);
        freeFeatsTextView.setText(getNumberOfFreeFeats());
    }

    private String getNumberOfFreeFeats() {
        final int numberOfFeats = featModel.getCharacterFeats().size();
        final int maxNumberOfFeats = ruleService.getNumberOfFeats(character);
        return numberOfFeats +
                SPACE +
                getResources().getString(R.string.feat_list_preposition) +
                SPACE +
                maxNumberOfFeats +
                SPACE +
                getResources().getString(R.string.feat_list_feats);
    }

    private void setTabs() {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        addTab(R.string.feat_type_general, R.id.feat_list_general, android.R.drawable.ic_menu_view);
        addTab(R.string.feat_type_item_creation, R.id.feat_list_item_creation, android.R.drawable.ic_menu_manage);
        addTab(R.string.feat_type_metamagic, R.id.feat_list_metamagic, android.R.drawable.ic_menu_compass);
        addTab(R.string.feat_type_teamwork, R.id.feat_list_teamwork, android.R.drawable.ic_menu_compass);

        tabHost.setCurrentTab(0);

        featListOnTabChangeListener = new FeatListOnTabChangeListener(featModel);
        tabHost.setOnTabChangedListener(featListOnTabChangeListener);
    }

    private void setFeatListView(final FeatType featType, final int listViewResourceId) {
        final FeatListAdapter featListAdapter = new FeatListAdapter(Objects.requireNonNull(getActivity()), R.layout.listitem_feat, featModel,
                featType);
        final ListView listView = view.findViewById(listViewResourceId);
        listView.setAdapter(featListAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new ExpandOnClickListener());
        listView.setOnCreateContextMenuListener(new FeatContextMenu(this, featModel, getResources()));
        featListOnTabChangeListener.addListView(listView);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {

        // see which child activity is calling us back.
        if (requestCode == RESULT_CODE_CATEGORY) {
            if (resultCode != Activity.RESULT_CANCELED) {
                editFeatCategory(resultIntent);
            }
        } else {
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    private void editFeatCategory(final Intent resultIntent) {
        final Bundle bundle = resultIntent.getExtras();
        final String category = (String) Objects.requireNonNull(bundle).get(INTENT_EXTRA_DATA_OBJECT);
        featModel.setCategory(category);
    }

    @Override
    public void update(final Observable observable, final Object data) {
        Logger.debug(getClass().getSimpleName() + ".update");
        setNumberOfFeats();
        final List<CharacterFeat> characterFeats = featModel.getCharacterFeats();
        character.setCharacterFeats(characterFeats);
        characterService.updateFeats(character);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_page_feat, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_page_feat_show_all);
        final CheckBox showAllCheckBox = (CheckBox) menuItem.getActionView();
        showAllCheckBox.setText(getString(R.string.feat_list_show_all_feats));
        showAllCheckBox.setChecked(featModel.isShowAllFeats());
        showAllCheckBox.setOnClickListener(new CheckBoxFeatModelController(featModel, showAllCheckBox));
    }

}
