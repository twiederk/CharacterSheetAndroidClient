package com.android.ash.charactersheet.gui.sheet;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.knownspell.CheckBoxSpelllistModelController;
import com.android.ash.charactersheet.gui.sheet.knownspell.KnownSpellPageModel;
import com.android.ash.charactersheet.gui.sheet.knownspell.LevelItem;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpellActivity;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpellItem;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpelllistAdapter;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpelllistModel;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * The KnownSpellPageFragment displays a tab for each spell list the character can access. The checkbox show all,
 * decides if all spells are listed or only the one known by the character. Known spells are added by checking the check
 * box in front of the spell name. Each heading of a spell level shows how many spells the character currently knowns
 * and how many he is allowed to know. Touch on a spell opens the detail info of the spell.
 */
public class KnownSpellPageFragment extends PageFragment implements OnItemClickListener {

    private KnownSpellPageModel knownSpellPageModel;

    @Override
    protected int getLayoutId() {
        return R.layout.page_known_spell;
    }

    @Override
    protected void doCreateView() {
        Logger.debug(getClass().getSimpleName() + ".doCreateView");
        if (!character.getSpelllists().isEmpty()) {
            createLayout();
        }
    }

    private void createLayout() {
        createModel();
        createSpelllistTabs();
    }

    private void createModel() {
        knownSpellPageModel = new KnownSpellPageModel();
        for (final Spelllist spelllist : character.getSpelllists()) {
            final List<KnownSpell> knownSpells = character.getKnownSpells(spelllist);
            final SpelllistAbility spelllistAbility = getSpelllistAbility(spelllist);
            final SpelllistModel spelllistModel = new SpelllistModel(knownSpellPageModel, spelllistAbility, knownSpells);
            knownSpellPageModel.addSpelllistModel(spelllistModel);
        }
        final boolean showAll = character.getKnownSpells().isEmpty();
        knownSpellPageModel.setShowAll(showAll);
    }

    private SpelllistAbility getSpelllistAbility(final Spelllist spelllist) {
        for (final SpelllistAbility spelllistAbility : character.getSpelllistAbilities()) {
            if (spelllistAbility.getSpelllist().equals(spelllist)) {
                return spelllistAbility;
            }
        }
        throw new IllegalArgumentException("Can't find SpelllistAbility of spell list: " + spelllist);
    }

    private void createSpelllistTabs() {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        final TabHost.TabContentFactory tabContenctFactory = new SpelllistTabContentFactory(this);

        for (final Spelllist spelllist : character.getSpelllists()) {
            final TabSpec tabSpec = tabHost.newTabSpec(spelllist.getName());
            tabSpec.setIndicator(spelllist.getName());
            tabSpec.setContent(tabContenctFactory);
            tabHost.addTab(tabSpec);
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final Object item = adapterView.getItemAtPosition(position);
        if (item instanceof SpellItem) {
            final SpellItem spellItem = (SpellItem) item;
            final Intent intent = new Intent(getActivity(), SpellActivity.class);
            intent.putExtra(INTENT_EXTRA_DATA_OBJECT, spellItem.getSpell().getId());
            startActivity(intent);
        } else if (item instanceof LevelItem) {
            final LevelItem levelItem = (LevelItem) item;
            levelItem.setExpanded(!levelItem.isExpanded());
            final BaseAdapter adapter = (BaseAdapter) adapterView.getAdapter();
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (character.getSpelllists().isEmpty()) {
            return;
        }

        inflater.inflate(R.menu.menu_page_known_spell, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_page_known_spell_show_all);
        final CheckBox showAllCheckBox = (CheckBox) menuItem.getActionView();
        showAllCheckBox.setText(getString(R.string.page_known_spell_menu_show_all));
        showAllCheckBox.setChecked(knownSpellPageModel.isShowAll());
        showAllCheckBox.setOnClickListener(new CheckBoxSpelllistModelController(knownSpellPageModel));
    }

    /**
     * Creates a tab for each spell list the character has access to.
     */
    class SpelllistTabContentFactory implements TabHost.TabContentFactory {

        private final HashMap<String, ListView> tabs;

        /**
         * Creates tab for a spell list. The OnItemClickListener is used for clicks on a spell.
         * 
         * @param onItemClickListener
         *            Called if a spell is clicked.
         */
        SpelllistTabContentFactory(final OnItemClickListener onItemClickListener) {
            tabs = new HashMap<>();

            for (final SpelllistModel spelllistModel : knownSpellPageModel.getSpelllistModels()) {

                final SpelllistAdapter spelllistAdapter = new SpelllistAdapter(getActivity(), displayService,
                        characterService, ruleService, spelllistModel, knownSpellPageModel, character);
                final ListView listView = new ListView(getActivity());
                listView.setAdapter(spelllistAdapter);
                listView.setOnItemClickListener(onItemClickListener);
                listView.setDrawSelectorOnTop(false);
                listView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.paperscroll));
                listView.setCacheColorHint(0);

                tabs.put(spelllistModel.getName(), listView);
            }

        }

        @Override
        public View createTabContent(final String tag) {
            return tabs.get(tag);
        }

    }

}
