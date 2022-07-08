package com.android.ash.charactersheet.gui.sheet;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.skill.HideOnTabChangeListener;
import com.android.ash.charactersheet.gui.sheet.skill.SkillDescriptionActivity;
import com.android.ash.charactersheet.gui.sheet.skill.SkillEditActivity;
import com.android.ash.charactersheet.gui.util.HideOnClickListener;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.FavoriteCharacterSkill;
import com.d20charactersheet.framework.boc.util.CharacterSkillComparator;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

/**
 * The SkillListActivity displays the max class rank and max cross class rank of the character. It displays the skill
 * points of the character. The skills are displayed in three different skill lists. Each skill list is contained in a
 * separate tab. The option menu allows to call EditSkillActivity. The context menu of the skill lists allow to add or
 * remove them from favorite skill list.
 */
public abstract class AbstractSkillPageFragment extends PageFragment implements OnItemClickListener {

    protected static final int CONTEXT_MENU_ADD_TO_FAVORITES = 10;
    protected static final int CONTEXT_MENU_REMOVE_FROM_FAVORITES = 20;

    private FavoriteCharacterSkill selectedSkill;

    private ArrayAdapter<CharacterSkill> favoriteSkillsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.page_skill;
    }

    @Override
    protected void doCreateView() {
        setTabs();
        setSkillRoll();
    }

    protected abstract void setHeader();

    private void setTabs() {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        addTab(R.string.skill_list_tab_title_fav, R.id.skill_list_list_favorite, android.R.drawable.btn_star);
        addTab(R.string.skill_list_tab_title_trained, R.id.skill_list_list_trained, android.R.drawable.ic_menu_preferences);
        addTab(R.string.skill_list_tab_title_all, R.id.skill_list_list_all, android.R.drawable.ic_menu_sort_alphabetically);
        tabHost.setCurrentTab(0);
    }

    private void setFavoriteSkills() {
        final List<CharacterSkill> favoriteSkills = getFavoriteCharacterSkills();
        favoriteSkillsAdapter = createAdapterAndListView(favoriteSkills, R.id.skill_list_list_favorite);
    }

    private void setTrainedSkills() {
        final List<CharacterSkill> trainedSkills = getTrainedSkills();
        createAdapterAndListView(trainedSkills, R.id.skill_list_list_trained);
    }

    private void setAllSkills() {
        List<CharacterSkill> allSkills = character.getCharacterSkills();
        createAdapterAndListView(allSkills, R.id.skill_list_list_all);
    }

    private ArrayAdapter<CharacterSkill> createAdapterAndListView(final List<CharacterSkill> characterSkills,
                                                                  final int listViewResourceId) {
        final DieRollView dieRollView = view.findViewById(R.id.die_roll_view);
        final DisplayArrayAdapter<CharacterSkill> characterSkillsArrayAdapter = createCharacterSkillArrayAdapter(dieRollView, characterSkills);
        characterSkillsArrayAdapter.sort(new CharacterSkillComparator());

        final ListView listView = view.findViewById(listViewResourceId);
        listView.setAdapter(characterSkillsArrayAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
        listView.setOnItemClickListener(this);

        return characterSkillsArrayAdapter;
    }

    protected abstract DisplayArrayAdapter<CharacterSkill> createCharacterSkillArrayAdapter(DieRollView dieRollView, List<CharacterSkill> characterSkills);

    private List<CharacterSkill> getTrainedSkills() {
        final List<CharacterSkill> allSkills = character.getCharacterSkills();
        List<CharacterSkill> trainedSkills = new ArrayList<>();
        for (final CharacterSkill skill : allSkills) {
            if (ruleService.isTrained(skill)) {
                trainedSkills.add(skill);
            }
        }
        return trainedSkills;
    }

    private List<CharacterSkill> getFavoriteCharacterSkills() {
        final List<CharacterSkill> allSkills = character.getCharacterSkills();
        List<CharacterSkill> favoriteSkills = new ArrayList<>();
        for (final CharacterSkill characterSkill : allSkills) {
            if (characterSkill instanceof FavoriteCharacterSkill) {
                final FavoriteCharacterSkill favCharacterSkill = (FavoriteCharacterSkill) characterSkill;
                if (favCharacterSkill.isFavorite()) {
                    favoriteSkills.add(favCharacterSkill);
                }
            }
        }
        return favoriteSkills;
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, @NonNull final View view, final ContextMenuInfo menuInfo) {
        selectedSkill = (FavoriteCharacterSkill) getSelectedSkill(view, menuInfo);

        menu.setHeaderTitle(selectedSkill.getSkill().getName());
        if (selectedSkill.isFavorite()) {
            menu.add(Menu.NONE, CONTEXT_MENU_REMOVE_FROM_FAVORITES, Menu.CATEGORY_SECONDARY,
                    R.string.skill_list_remove_from_favorites);
        } else {
            menu.add(Menu.NONE, CONTEXT_MENU_ADD_TO_FAVORITES, Menu.CATEGORY_SECONDARY,
                    R.string.skill_list_add_to_favorites);
        }
    }

    private CharacterSkill getSelectedSkill(final View view, final ContextMenuInfo menuInfo) {
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final ListView listView = (ListView) view;
        return (CharacterSkill) listView.getAdapter().getItem(info.position);
    }

    /**
     * Adds or removes skill from favorite skill list.
     */
    @Override
    public boolean onContextItemSelected(final MenuItem menuItem) {
        menuItem.getMenuInfo();
        switch (menuItem.getItemId()) {
            case CONTEXT_MENU_ADD_TO_FAVORITES:
                addSkillToFavorites();
                return true;

            case CONTEXT_MENU_REMOVE_FROM_FAVORITES:
                removeSkillFromFavorites();
                return true;

            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void addSkillToFavorites() {
        if (!selectedSkill.isFavorite()) {
            selectedSkill.setFavorite(true);
            favoriteSkillsAdapter.add(selectedSkill);
            favoriteSkillsAdapter.sort(new CharacterSkillComparator());
            characterService.updateCharacterSkill(character, selectedSkill);
        }
    }

    private void removeSkillFromFavorites() {
        selectedSkill.setFavorite(false);
        favoriteSkillsAdapter.remove(selectedSkill);
        characterService.updateCharacterSkill(character, selectedSkill);
    }

    private void showDescription(final CharacterSkill characterSkill) {
        final Intent intent = new Intent(getActivity(), SkillDescriptionActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, characterSkill.getSkill().getId());
        startActivity(intent);
    }

    /**
     * The option menu offers the menu item to edit the skills in the EditSkillActivity.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, final MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_page_skill, menu);
    }

    /**
     * Option menu leads to skill edit if selected.
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.menu_page_skill_edit_skills) {
            jumpToEditSkills();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void jumpToEditSkills() {
        final Intent intent = new Intent(getActivity(), SkillEditActivity.class);
        startActivity(intent);
    }

    private void setSkillRoll() {
        final View skillRollView = view.findViewById(R.id.die_roll_view);
        skillRollView.setOnClickListener(new HideOnClickListener());
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setOnTabChangedListener(new HideOnTabChangeListener(skillRollView));
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.SKILL);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "SkillPageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        setHeader();
        setFavoriteSkills();
        setTrainedSkills();
        setAllSkills();
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final CharacterSkill characterSkill = (CharacterSkill) adapterView.getAdapter().getItem(position);
        showDescription(characterSkill);
    }

}
