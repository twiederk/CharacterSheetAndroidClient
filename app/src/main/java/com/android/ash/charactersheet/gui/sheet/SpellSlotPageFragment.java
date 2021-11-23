package com.android.ash.charactersheet.gui.sheet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.spellslot.SpellSlotAdapter;
import com.android.ash.charactersheet.gui.sheet.spellslot.SpellSlotModel;
import com.android.ash.charactersheet.gui.sheet.spellslot.SpellSlotOnCreateContextMenuListener;
import com.android.ash.charactersheet.gui.sheet.spellslot.SpellSlotOnItemClickListener;
import com.android.ash.charactersheet.gui.sheet.spellslot.SpellSlotPageModel;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Displays a tab for each spell caster class of the character. Each tab displays the spell slots of the character for
 * this class.
 */
public class SpellSlotPageFragment extends PageFragment {

    /** The request code to return from SpellSlotActivity */
    public static final int REQUEST_CODE = 10;

    private SpellSlotPageModel spellSlotPageModel;
    private SpellSlotOnCreateContextMenuListener contextMenuListener;
    private boolean createForFirstTime;

    @Override
    protected int getLayoutId() {
        return R.layout.page_spell_slot;
    }

    @Override
    protected void doCreateView() {
        Logger.debug(getClass().getSimpleName() + ".doCreateView");
        createForFirstTime = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.SPELL_SLOT);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "SpellSlotPageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        if (!character.getSpelllists().isEmpty() && (createForFirstTime || isSpellSlotsChanged())) {
            createLayout();
        }
    }

    private boolean isSpellSlotsChanged() {
        final List<SpellSlot> characterSpellSlots = new LinkedList<>(character.getSpellSlots());
        final List<SpellSlot> calculatedSpellSlots = ruleService.calculateSpellSlots(character);
        if (characterSpellSlots.size() != calculatedSpellSlots.size()) {
            return true;
        }

        for (final SpellSlot calculatedSpellSlot : calculatedSpellSlots) {
            final SpellSlot characterSpellSlot = findSpellSlot(calculatedSpellSlot, characterSpellSlots);
            if (characterSpellSlot == null) {
                return true;
            }
            characterSpellSlots.remove(characterSpellSlot);
        }
        return false;
    }

    private SpellSlot findSpellSlot(final SpellSlot calculatedSpellSlot, final List<SpellSlot> characterSpellSlots) {
        for (final SpellSlot characterSpellSlot : characterSpellSlots) {
            if (equals(calculatedSpellSlot, characterSpellSlot)) {
                return characterSpellSlot;
            }
        }
        return null;
    }

    private boolean equals(final SpellSlot spellSlot, final SpellSlot characterSpellSlot) {
        return spellSlot.getLevel() == characterSpellSlot.getLevel()
                && equals(spellSlot.getSpelllistAbilities(), characterSpellSlot.getSpelllistAbilities());
    }

    private boolean equals(final List<SpelllistAbility> spelllistAbilities,
            final List<SpelllistAbility> characterSpelllistAbilities) {
        if (spelllistAbilities.size() != characterSpelllistAbilities.size()) {
            return false;
        }
        for (final SpelllistAbility spelllistAbility : spelllistAbilities) {
            if (!characterSpelllistAbilities.contains(spelllistAbility)) {
                return false;
            }
        }
        return true;
    }

    private void createLayout() {
        createForFirstTime = false;
        createModel();
        createClassTabs();
    }

    private void createModel() {
        final List<SpellSlot> calculatedSpellSlots = ruleService.calculateSpellSlots(character);
        characterService.updateSpellSlots(character, calculatedSpellSlots);

        spellSlotPageModel = new SpellSlotPageModel();
        final List<SpellSlot> allSpellSlots = new LinkedList<>(character.getSpellSlots());
        for (final ClassLevel classLevel : character.getClassLevels()) {
            final CharacterClass clazz = classLevel.getCharacterClass();
            final List<SpelllistAbility> spelllistAbilities = getSpelllistAbilitiesOfClass(clazz);
            if (!spelllistAbilities.isEmpty()) {
                final List<SpellSlot> spellSlots = getSpellSlotsOfSpelllistAbilities(spelllistAbilities, allSpellSlots);
                final Attribute spellAttribute = spelllistAbilities.get(0).getSpellAttribute();
                final SpellSlotModel spellSlotModel = new SpellSlotModel(classLevel, spellAttribute, spellSlots);
                spellSlotPageModel.addSpellSlotModel(spellSlotModel);
                allSpellSlots.removeAll(spellSlots);
            }
        }
        if (!allSpellSlots.isEmpty()) {
            throw new IllegalStateException("Not all spell slots could be assigned to classes: " + allSpellSlots);
        }

    }

    private List<SpelllistAbility> getSpelllistAbilitiesOfClass(final CharacterClass clazz) {
        final List<SpelllistAbility> spelllistAbilities = new LinkedList<>();
        for (final ClassAbility classAbility : clazz.getClassAbilities()) {
            if (classAbility.getAbility() instanceof SpelllistAbility) {
                final SpelllistAbility spelllistAbility = (SpelllistAbility) classAbility.getAbility();
                spelllistAbilities.add(spelllistAbility);
            }
        }
        return spelllistAbilities;
    }

    private List<SpellSlot> getSpellSlotsOfSpelllistAbilities(final List<SpelllistAbility> spelllistAbilities,
            final List<SpellSlot> allSpellSlots) {
        final List<SpellSlot> spellSlots = new ArrayList<>();
        for (final SpellSlot spellSlot : allSpellSlots) {
            if (contains(spellSlot, spelllistAbilities)) {
                spellSlots.add(spellSlot);
            }
        }
        return spellSlots;
    }

    private boolean contains(final SpellSlot spellSlot, final List<SpelllistAbility> spelllistAbilities) {
        for (final SpelllistAbility spelllistAbility : spelllistAbilities) {
            if (spellSlot.getSpelllistAbilities().contains(spelllistAbility)) {
                return true;
            }
        }
        return false;
    }

    private void createClassTabs() {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.clearAllTabs();

        contextMenuListener = new SpellSlotOnCreateContextMenuListener(getActivity());
        final TabHost.TabContentFactory tabContentFactory = new ClassTabContentFactory(
                new SpellSlotOnItemClickListener(this, characterService), contextMenuListener);

        for (final SpellSlotModel spellSlotModel : spellSlotPageModel.getSpellSlotModels()) {
            final TabSpec tabSpec = tabHost.newTabSpec(spellSlotModel.getName());
            tabSpec.setIndicator(spellSlotModel.getName());
            tabSpec.setContent(tabContentFactory);
            tabHost.addTab(tabSpec);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem menuItem) {
        return contextMenuListener.onContextItemSelected(menuItem);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            spellSlotPageModel.resumeFromSpellSlotActivity();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        if (!character.getSpelllists().isEmpty()) {
            inflater.inflate(R.menu.menu_page_spell_slot, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == R.id.menu_page_spell_slot_rest) {
            rest();
        }
        return super.onOptionsItemSelected(item);
    }

    private void rest() {
        Toast.makeText(getActivity(), R.string.page_spell_slot_toast_resting, Toast.LENGTH_SHORT).show();
        final List<SpellSlot> spellSlots = ruleService.rest(character);
        for (final SpellSlot spellSlot : spellSlots) {
            characterService.updateSpellSlot(spellSlot);
        }
        spellSlotPageModel.resumeFromSpellSlotActivity();
    }

    /**
     * Creates tabs for each spell caster class of the character.
     */
    class ClassTabContentFactory implements TabHost.TabContentFactory {

        private final HashMap<String, View> tabs;

        /**
         * Creates tabs for each spell caster class of the character.
         * 
         * @param onItemClickListener
         *            The item listener of spell slots.
         * @param onCreateContextMenuListener
         *            The create context menu listener.
         */
        ClassTabContentFactory(final OnItemClickListener onItemClickListener,
                               final OnCreateContextMenuListener onCreateContextMenuListener) {
            tabs = new HashMap<>();

            for (final SpellSlotModel spellSlotModel : spellSlotPageModel.getSpellSlotModels()) {
                final View tabView = inflateTabView();

                setClassLevel(tabView, spellSlotModel);
                setSpellAttribute(tabView, spellSlotModel);
                setSpellSlots(tabView, spellSlotModel, onItemClickListener, onCreateContextMenuListener);

                tabs.put(spellSlotModel.getName(), tabView);
            }

        }

        private void setClassLevel(final View tabView, final SpellSlotModel spellSlotModel) {
            final String text = displayService.getDisplayClassLevel(spellSlotModel.getClassLevel());
            final TextView classLevelTextView = tabView.findViewById(R.id.page_spell_slot_class_level);
            classLevelTextView.setText(text);
        }

        private void setSpellAttribute(final View tabView, final SpellSlotModel spellSlotModel) {
            final Attribute attribute = spellSlotModel.getSpellAttribute();
            final StringBuilder text = new StringBuilder();
            text.append(displayService.getDisplayAttributeShort(attribute));
            text.append(": ");
            text.append(character.getAttribute(attribute));
            final TextView attributeTextView = tabView.findViewById(R.id.page_spell_slot_attribute);
            attributeTextView.setText(text);
        }

        private void setSpellSlots(final View tabView, final SpellSlotModel spellSlotModel,
                final OnItemClickListener onItemClickListener,
                final OnCreateContextMenuListener onCreateContextMenuListener) {
            final SpellSlotAdapter spelllistAdapter = new SpellSlotAdapter(getActivity(), character, displayService,
                    ruleService, spellSlotModel);
            // final ListView listView = new ListView(getActivity());
            final ListView listView = tabView.findViewById(android.R.id.list);
            listView.setAdapter(spelllistAdapter);
            listView.setOnItemClickListener(onItemClickListener);
            listView.setOnCreateContextMenuListener(onCreateContextMenuListener);
            listView.setDrawSelectorOnTop(false);
            listView.setCacheColorHint(0);
        }

        private View inflateTabView() {
            final TabHost tabHost = view.findViewById(android.R.id.tabhost);
            final LayoutInflater layoutInflater = (LayoutInflater) requireActivity().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            return Objects.requireNonNull(layoutInflater).inflate(R.layout.page_spell_slot_tab, tabHost, false);
        }

        @Override
        public View createTabContent(final String tag) {
            return tabs.get(tag);
        }

    }

}
