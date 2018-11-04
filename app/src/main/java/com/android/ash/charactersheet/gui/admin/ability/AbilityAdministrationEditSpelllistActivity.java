package com.android.ash.charactersheet.gui.admin.ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.EnumSpinnerAdapter;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.CastingType;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.SpellSource;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.boc.util.KnownSpellsTableComparator;
import com.d20charactersheet.framework.boc.util.SpelllistComparator;
import com.d20charactersheet.framework.boc.util.SpellsPerDayTableComparator;

/**
 * Administration of SpelllistAbilities with drop down list of spell lists.
 */
public class AbilityAdministrationEditSpelllistActivity extends AbilityAdministrationEditActivity {

    @Override
    protected int getLayout() {
        return R.layout.ability_administration_edit_spelllist;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.ability_administration_edit_title_spelllist);
    }

    @Override
    protected void fillViews() {
        super.fillViews();
        setSpelllist();
        setSpellAttribute();
        setCastingType();
        setSpellSource();
        setSchool();
        setKnownSpellsTable();
        setSpellsPerDayTable();
    }

    private void setSpelllist() {
        final List<Spelllist> allSpelllists = gameSystem.getAllSpelllists();
        Collections.sort(allSpelllists, new SpelllistComparator());
        final SpinnerAdapter adapter = new NameDisplayArrayAdapter<Spelllist>(this, displayService, allSpelllists);
        final int position = getSpelllistPosition(allSpelllists);
        setSpinner(R.id.ability_administration_spelllist, adapter, position);
    }

    private int getSpelllistPosition(final List<Spelllist> allSpelllists) {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int spelllistId = spelllistAbility.getSpelllist().getId();
        for (int i = 0; i < allSpelllists.size(); i++) {
            if (allSpelllists.get(i).getId() == spelllistId) {
                return i;
            }
        }
        throw new IllegalArgumentException("Can't find spelllist with id: " + spelllistId);
    }

    private void setSpellAttribute() {
        final List<Attribute> attributes = Arrays.asList(Attribute.values());
        final List<Enum<?>> enumAttributes = new ArrayList<Enum<?>>(attributes);

        final SpinnerAdapter attributeAdapter = new EnumSpinnerAdapter(this, displayService, enumAttributes) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplayAttribute((Attribute) enumeration);
            }

        };
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int position = getEnumPosition(enumAttributes, spelllistAbility.getSpellAttribute());
        setSpinner(R.id.ability_administration_attribute, attributeAdapter, position);
    }

    private void setCastingType() {
        final List<CastingType> castingTypes = Arrays.asList(CastingType.values());
        final List<Enum<?>> enumCastingTypes = new ArrayList<Enum<?>>(castingTypes);

        final SpinnerAdapter castingTypeAdapter = new EnumSpinnerAdapter(this, displayService, enumCastingTypes) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplayCastingType((CastingType) enumeration);
            }

        };
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int position = getEnumPosition(enumCastingTypes, spelllistAbility.getCastingType());
        setSpinner(R.id.ability_administration_castingtype, castingTypeAdapter, position);
    }

    private void setSpellSource() {
        final List<SpellSource> spellSources = Arrays.asList(SpellSource.values());
        final List<Enum<?>> enumSpellSources = new ArrayList<Enum<?>>(spellSources);

        final SpinnerAdapter spellSourceAdapter = new EnumSpinnerAdapter(this, displayService, enumSpellSources) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplaySpellSource((SpellSource) enumeration);
            }

        };
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int position = getEnumPosition(enumSpellSources, spelllistAbility.getSpellSource());
        setSpinner(R.id.ability_administration_spellsource, spellSourceAdapter, position);
    }

    private void setSchool() {
        final List<String> allSchoolNames = getAllSchoolNames();
        Collections.sort(allSchoolNames);
        final SpinnerAdapter adapter = new NameDisplayArrayAdapter<String>(this, displayService, allSchoolNames);
        final int position = getSchoolPosition(allSchoolNames);
        setSpinner(R.id.ability_administration_school, adapter, position);
    }

    private List<String> getAllSchoolNames() {
        final School[] allSchools = School.values();
        final List<String> schoolNames = new ArrayList<String>(allSchools.length + 1);
        for (final School school : allSchools) {
            schoolNames.add(displayService.getDisplaySchool(school));
        }
        schoolNames.add(getResources().getString(R.string.ability_administration_edit_any_school));
        return schoolNames;
    }

    private int getSchoolPosition(final List<String> allSchoolNames) {
        final String schoolName = getSchoolName();
        for (int i = 0; i < allSchoolNames.size(); i++) {
            if (allSchoolNames.get(i).equals(schoolName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Can't find school with name: " + schoolName);
    }

    private String getSchoolName() {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        String currentName = getResources().getString(R.string.ability_administration_edit_any_school);
        final School[] schools = new School[spelllistAbility.getSchools().size()];
        spelllistAbility.getSchools().toArray(schools);
        if (schools.length == 1) {
            currentName = displayService.getDisplaySchool(schools[0]);
        }
        return currentName;
    }

    private void setKnownSpellsTable() {
        final List<KnownSpellsTable> allKnownSpellsTables = gameSystem.getAllKnownSpellsTables();
        Collections.sort(allKnownSpellsTables, new KnownSpellsTableComparator());
        final SpinnerAdapter adapter = new NameDisplayArrayAdapter<KnownSpellsTable>(this, displayService,
                allKnownSpellsTables);
        final int position = getKnownSpellsTablePosition(allKnownSpellsTables);
        setSpinner(R.id.ability_administration_known_spells_table, adapter, position);
    }

    private int getKnownSpellsTablePosition(final List<KnownSpellsTable> allKnownSpellsTables) {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int knownSpellsTableId = spelllistAbility.getKnownSpellsTable().getId();
        for (int i = 0; i < allKnownSpellsTables.size(); i++) {
            if (allKnownSpellsTables.get(i).getId() == knownSpellsTableId) {
                return i;
            }
        }
        throw new IllegalArgumentException("Can't find known spells table with id: " + knownSpellsTableId);
    }

    private void setSpellsPerDayTable() {
        final List<SpellsPerDayTable> allSpellsPerDayTables = gameSystem.getAllSpellsPerDayTables();
        Collections.sort(allSpellsPerDayTables, new SpellsPerDayTableComparator());
        final SpinnerAdapter adapter = new NameDisplayArrayAdapter<SpellsPerDayTable>(this, displayService,
                allSpellsPerDayTables);
        final int position = getSpellsPerDayPosition(allSpellsPerDayTables);
        setSpinner(R.id.ability_administration_spells_per_day_table, adapter, position);
    }

    private int getSpellsPerDayPosition(final List<SpellsPerDayTable> allSpellsPerDayTables) {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        final int spellsPerDayTableId = spelllistAbility.getSpellsPerDayTable().getId();
        for (int i = 0; i < allSpellsPerDayTables.size(); i++) {
            if (allSpellsPerDayTables.get(i).getId() == spellsPerDayTableId) {
                return i;
            }
        }
        throw new IllegalArgumentException("Can't find spells per day table with id: " + spellsPerDayTableId);
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        final SpelllistAbility spelllistAbility = (SpelllistAbility) form;
        spelllistAbility.setSpelllist((Spelllist) getSelectedItemOfSpinner(R.id.ability_administration_spelllist));
        spelllistAbility.setSpellAttribute((Attribute) getSelectedItemOfSpinner(R.id.ability_administration_attribute));
        spelllistAbility
                .setCastingType((CastingType) getSelectedItemOfSpinner(R.id.ability_administration_castingtype));
        spelllistAbility
                .setSpellSource((SpellSource) getSelectedItemOfSpinner(R.id.ability_administration_spellsource));
        spelllistAbility.setSchools(getSelectedSchool());
        spelllistAbility
                .setKnownSpellsTable((KnownSpellsTable) getSelectedItemOfSpinner(R.id.ability_administration_known_spells_table));
        spelllistAbility
                .setSpellsPerDayTable((SpellsPerDayTable) getSelectedItemOfSpinner(R.id.ability_administration_spells_per_day_table));
    }

    private EnumSet<School> getSelectedSchool() {
        final String schoolName = (String) getSelectedItemOfSpinner(R.id.ability_administration_school);
        if (schoolName.equalsIgnoreCase(getResources().getString(R.string.ability_administration_edit_any_school))) {
            return EnumSet.allOf(School.class);
        }
        for (final School school : School.values()) {
            if (schoolName.equals(displayService.getDisplaySchool(school))) {
                return EnumSet.of(school);
            }
        }
        throw new IllegalArgumentException("Can't find school with name: " + schoolName);
    }
}
