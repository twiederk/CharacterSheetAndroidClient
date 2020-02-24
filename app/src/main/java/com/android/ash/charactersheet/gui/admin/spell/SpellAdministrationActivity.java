package com.android.ash.charactersheet.gui.admin.spell;

import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.EnumSpinnerAdapter;
import com.d20charactersheet.framework.boc.model.CastingTime;
import com.d20charactersheet.framework.boc.model.Descriptor;
import com.d20charactersheet.framework.boc.model.Range;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellResistance;
import com.d20charactersheet.framework.boc.model.SubSchool;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Mask to administer spells.
 */
public abstract class SpellAdministrationActivity extends FormActivity<Spell> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    GameSystem gameSystem;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.spell_administration;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
    }

    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.spell_administration_name);
        setSchoolSpinner();
        setSubSchoolSpinner();
        setDescriptorCheckBoxes();
        setComponentCheckBoxes();
        setCastingTimeSpinner();
        setRangeSpinner();
        setText(form.getEffect(), R.id.spell_administration_effect);
        setText(form.getDuration(), R.id.spell_administration_duration);
        setText(form.getSavingThrow(), R.id.spell_administration_savingthrow);
        setSpellResistanceSpinner();
        setText(form.getMaterialComponent(), R.id.spell_administration_materialcomponent);
        setText(form.getFocus(), R.id.spell_administration_focus);
        setText(form.getShortDescription(), R.id.spell_administration_shortdescription);
        setText(form.getDescription(), R.id.spell_administration_description);
    }

    private void setSchoolSpinner() {
        final List<School> schools = Arrays.asList(School.values());
        final List<Enum<?>> sortedSchools = new ArrayList<>(schools);
        final SpinnerAdapter schoolAdapter = new EnumSpinnerAdapter(this, displayService, sortedSchools) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplaySchool((School) enumeration);
            }

        };
        final int position = getEnumPosition(sortedSchools, form.getSchool());
        setSpinner(R.id.spell_administration_school, schoolAdapter, position);
    }

    private void setSubSchoolSpinner() {
        final List<SubSchool> subSchools = Arrays.asList(SubSchool.values());
        final List<Enum<?>> sortedSubSchools = new ArrayList<>(subSchools);
        final SpinnerAdapter subSchoolAdapter = new EnumSpinnerAdapter(this, displayService, sortedSubSchools) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplaySubSchool((SubSchool) enumeration);
            }

        };
        final int position = getEnumPosition(sortedSubSchools, form.getSubSchool());
        setSpinner(R.id.spell_administration_subschool, subSchoolAdapter, position);
    }

    private void setDescriptorCheckBoxes() {
        for (final Descriptor descriptor : form.getDescriptors()) {
            setDescriptorCheckBox(descriptor);
        }
    }

    private void setDescriptorCheckBox(final Descriptor descriptor) {
        switch (descriptor) {
        case ACID:
            setCheckBox(true, R.id.spell_administration_descriptor_acid);
            break;

        case AIR:
            setCheckBox(true, R.id.spell_administration_descriptor_air);
            break;

        case CHAOTIC:
            setCheckBox(true, R.id.spell_administration_descriptor_chaotic);
            break;

        case COLD:
            setCheckBox(true, R.id.spell_administration_descriptor_cold);
            break;

        case DARKNESS:
            setCheckBox(true, R.id.spell_administration_descriptor_darkness);
            break;

        case DEATH:
            setCheckBox(true, R.id.spell_administration_descriptor_death);
            break;

        case EARTH:
            setCheckBox(true, R.id.spell_administration_descriptor_earth);
            break;

        case ELECTRICITY:
            setCheckBox(true, R.id.spell_administration_descriptor_electricity);
            break;

        case EVIL:
            setCheckBox(true, R.id.spell_administration_descriptor_evil);
            break;

        case FEAR:
            setCheckBox(true, R.id.spell_administration_descriptor_fear);
            break;

        case FIRE:
            setCheckBox(true, R.id.spell_administration_descriptor_fire);
            break;

        case FORCE:
            setCheckBox(true, R.id.spell_administration_descriptor_force);
            break;

        case GOOD:
            setCheckBox(true, R.id.spell_administration_descriptor_good);
            break;

        case LANGUAGE_DEPENDENT:
            setCheckBox(true, R.id.spell_administration_descriptor_languagedependent);
            break;

        case LAWFUL:
            setCheckBox(true, R.id.spell_administration_descriptor_lawful);
            break;

        case LIGHT:
            setCheckBox(true, R.id.spell_administration_descriptor_light);
            break;

        case MIND_AFFECTING:
            setCheckBox(true, R.id.spell_administration_descriptor_mindaffecting);
            break;

        case SONIC:
            setCheckBox(true, R.id.spell_administration_descriptor_sonic);
            break;

        case WATER:
            setCheckBox(true, R.id.spell_administration_descriptor_water);
            break;

        default:
            break;
        }
    }

    private void setComponentCheckBoxes() {
        setCheckBox(form.isVerbal(), R.id.spell_administration_component_verbal);
        setCheckBox(form.isSomatic(), R.id.spell_administration_component_somatic);
        setCheckBox(form.isMaterial(), R.id.spell_administration_component_material);
        setCheckBox(form.isFocus(), R.id.spell_administration_component_focus);
        setCheckBox(form.isDivineFocus(), R.id.spell_administration_component_divinefocus);
        setCheckBox(form.isXpCost(), R.id.spell_administration_component_xpcost);
    }

    private void setCastingTimeSpinner() {
        final List<CastingTime> castingTimes = Arrays.asList(CastingTime.values());
        Collections.sort(castingTimes);
        final List<Enum<?>> sortedCastingTimes = new ArrayList<>(castingTimes);
        final SpinnerAdapter castingTimeAdapter = new EnumSpinnerAdapter(this, displayService, sortedCastingTimes) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplayCastingTime((CastingTime) enumeration);
            }

        };
        final int position = getEnumPosition(sortedCastingTimes, form.getCastingTime());
        setSpinner(R.id.spell_administration_castingtime, castingTimeAdapter, position);
    }

    private void setRangeSpinner() {
        final List<Range> range = Arrays.asList(Range.values());
        Collections.sort(range);
        final List<Enum<?>> sortedRanges = new ArrayList<>(range);
        final SpinnerAdapter rangeAdapter = new EnumSpinnerAdapter(this, displayService, sortedRanges) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplayRange((Range) enumeration);
            }

        };
        final int position = getEnumPosition(sortedRanges, form.getRange());
        setSpinner(R.id.spell_administration_range, rangeAdapter, position);
    }

    private void setSpellResistanceSpinner() {
        final List<SpellResistance> spellResistance = Arrays.asList(SpellResistance.values());
        Collections.sort(spellResistance);
        final List<Enum<?>> sortedSpellResistance = new ArrayList<>(spellResistance);
        final SpinnerAdapter spellResistanceAdapter = new EnumSpinnerAdapter(this, displayService,
                sortedSpellResistance) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplaySpellResistance((SpellResistance) enumeration);
            }

        };
        final int position = getEnumPosition(sortedSpellResistance, form.getRange());
        setSpinner(R.id.spell_administration_spellresistance, spellResistanceAdapter, position);
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.spell_administration_name));
        form.setSchool((School) getSelectedItemOfSpinner(R.id.spell_administration_school));
        form.setSubSchool((SubSchool) getSelectedItemOfSpinner(R.id.spell_administration_subschool));
        form.setDescriptors(getSelectedDescriptors());
        form.setVerbal(isChecked(R.id.spell_administration_component_verbal));
        form.setSomatic(isChecked(R.id.spell_administration_component_somatic));
        form.setMaterial(isChecked(R.id.spell_administration_component_material));
        form.setFocus(isChecked(R.id.spell_administration_component_focus));
        form.setDivineFocus(isChecked(R.id.spell_administration_component_divinefocus));
        form.setXpCost(isChecked(R.id.spell_administration_component_xpcost));
        form.setCastingTime((CastingTime) getSelectedItemOfSpinner(R.id.spell_administration_castingtime));
        form.setRange((Range) getSelectedItemOfSpinner(R.id.spell_administration_range));
        form.setEffect(getTextOfTextView(R.id.spell_administration_effect));
        form.setDuration(getTextOfTextView(R.id.spell_administration_duration));
        form.setSavingThrow(getTextOfTextView(R.id.spell_administration_savingthrow));
        form.setSpellResistance((SpellResistance) getSelectedItemOfSpinner(R.id.spell_administration_spellresistance));
        form.setMaterialComponent(getTextOfTextView(R.id.spell_administration_materialcomponent));
        form.setFocus(getTextOfTextView(R.id.spell_administration_focus));
        form.setShortDescription(getTextOfTextView(R.id.spell_administration_shortdescription));
        form.setDescription(getTextOfTextView(R.id.spell_administration_description));
    }

    private Descriptor[] getSelectedDescriptors() {
        final List<Descriptor> descriptorList = new LinkedList<>();
        for (final Descriptor descriptor : Descriptor.values()) {
            if (isChecked(descriptor)) {
                descriptorList.add(descriptor);
            }
        }
        if (descriptorList.isEmpty()) {
            descriptorList.add(Descriptor.NONE);
        }

        final Descriptor[] descriptors = new Descriptor[descriptorList.size()];
        descriptorList.toArray(descriptors);
        return descriptors;
    }

    private boolean isChecked(final Descriptor descriptor) {
        switch (descriptor) {
        case ACID:
            return isChecked(R.id.spell_administration_descriptor_acid);
        case AIR:
            return isChecked(R.id.spell_administration_descriptor_air);
        case CHAOTIC:
            return isChecked(R.id.spell_administration_descriptor_chaotic);
        case COLD:
            return isChecked(R.id.spell_administration_descriptor_cold);
        case DARKNESS:
            return isChecked(R.id.spell_administration_descriptor_darkness);
        case DEATH:
            return isChecked(R.id.spell_administration_descriptor_death);
        case EARTH:
            return isChecked(R.id.spell_administration_descriptor_earth);
        case ELECTRICITY:
            return isChecked(R.id.spell_administration_descriptor_electricity);
        case EVIL:
            return isChecked(R.id.spell_administration_descriptor_evil);
        case FEAR:
            return isChecked(R.id.spell_administration_descriptor_fear);
        case FIRE:
            return isChecked(R.id.spell_administration_descriptor_fire);
        case FORCE:
            return isChecked(R.id.spell_administration_descriptor_force);
        case GOOD:
            return isChecked(R.id.spell_administration_descriptor_good);
        case LANGUAGE_DEPENDENT:
            return isChecked(R.id.spell_administration_descriptor_languagedependent);
        case LAWFUL:
            return isChecked(R.id.spell_administration_descriptor_lawful);
        case LIGHT:
            return isChecked(R.id.spell_administration_descriptor_light);
        case MIND_AFFECTING:
            return isChecked(R.id.spell_administration_descriptor_mindaffecting);
        case SONIC:
            return isChecked(R.id.spell_administration_descriptor_sonic);
        case WATER:
            return isChecked(R.id.spell_administration_descriptor_water);
        default:
            return false;
        }
    }

}
