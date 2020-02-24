package com.android.ash.charactersheet.gui.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.ability.AbilityAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.clazz.CharacterClassAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.feat.FeatAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.item.armor.ArmorAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.item.good.GoodAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.item.weapon.WeaponAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.race.RaceAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.skill.SkillAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.spell.SpellAdministrationListActivity;
import com.android.ash.charactersheet.gui.admin.spelllist.SpelllistAdministrationListActivity;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

/**
 * The administration contains a button for each administration sub menu.
 */
public class AdministrationMenuActivity extends LogAppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_menu);
        setToolbar();
        setButtonsOnClickListeners();

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.administration_menu_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void setButtonsOnClickListeners() {
        setButtonOnClickListener(R.id.administration_menu_race_button, RaceAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_character_class_button,
                CharacterClassAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_ability_button, AbilityAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_skill_button, SkillAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_feat_button, FeatAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_weapon_button, WeaponAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_armor_button, ArmorAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_good_button, GoodAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_spelllist_button, SpelllistAdministrationListActivity.class);
        setButtonOnClickListener(R.id.administration_menu_spell_button, SpellAdministrationListActivity.class);
    }

    private void setButtonOnClickListener(final int id, final Class<? extends Activity> activity) {
        final Button button = findViewById(id);
        button.setOnClickListener(new IntentOnClickListener(new Intent(this, activity)));
    }
}
