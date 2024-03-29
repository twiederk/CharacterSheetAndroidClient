package com.android.ash.charactersheet.gui.sheet;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

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

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.FBAnalytics;
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
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * List of feats. The feats are listed in three tabs. One tab for general feats, one for item creation and one for
 * meta magic. A checkbox is used to show all feats or only the feats of the character. The number of feat of the
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
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.FEAT);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "FeatPageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

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
        return numberOfFeats + SPACE + getResources().getString(R.string.feat_list_preposition) + SPACE + maxNumberOfFeats + SPACE + getResources().getString(R.string.feat_list_feats);
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
        final FeatListAdapter featListAdapter = new FeatListAdapter(requireActivity(), R.layout.listitem_feat, featModel,
                featType);
        final ListView listView = view.findViewById(listViewResourceId);
        listView.setAdapter(featListAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new ExpandOnClickListener());
        listView.setOnCreateContextMenuListener(new FeatContextMenu(this, featModel, getResources()));
        featListOnTabChangeListener.addListView(listView);
    }

    @SuppressWarnings("deprecation")
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

    @SuppressWarnings("deprecation")
    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_page_feat, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_page_feat_show_all);
        final CheckBox showAllCheckBox = (CheckBox) menuItem.getActionView();
        if (showAllCheckBox != null) {
            showAllCheckBox.setText(getString(R.string.feat_list_show_all_feats));
            showAllCheckBox.setChecked(featModel.isShowAllFeats());
            showAllCheckBox.setOnClickListener(new CheckBoxFeatModelController(featModel, showAllCheckBox));
        }
    }

}
