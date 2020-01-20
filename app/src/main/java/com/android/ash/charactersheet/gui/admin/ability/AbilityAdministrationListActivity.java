package com.android.ash.charactersheet.gui.admin.ability;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.ExtraFeatsAbility;
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.util.AbilityComparator;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Lists all abilities alphabetically and allow to create and edit them.
 */
public class AbilityAdministrationListActivity extends AdministrationListActivity<Ability> {

    @Override
    protected int getTitleResource() {
        return R.string.ability_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Ability> getAdapter() {
        final ArrayAdapter<Ability> adapter = new NameDisplayArrayAdapter<>(this, displayService,
                gameSystem.getAllAbilities());
        adapter.sort(new AbilityComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, AbilityAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Ability ability) {
        Intent intent;
        if (ability instanceof ExtraFeatsAbility) {
            intent = new Intent(this, AbilityAdministrationEditExtraFeatsActivity.class).putExtra(
                    INTENT_EXTRA_DATA_OBJECT, ability.getId());
        } else if (ability instanceof ExtraSkillPointsAbility) {
            intent = new Intent(this, AbilityAdministrationEditExtraSkillPointsActivity.class).putExtra(
                    INTENT_EXTRA_DATA_OBJECT, ability.getId());
        } else if (ability instanceof SpelllistAbility) {
            intent = new Intent(this, AbilityAdministrationEditSpelllistActivity.class).putExtra(
                    INTENT_EXTRA_DATA_OBJECT, ability.getId());
        } else {
            intent = new Intent(this, AbilityAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT,
                    ability.getId());
        }
        return intent;
    }
}
