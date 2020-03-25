package com.android.ash.charactersheet.gui.sheet.attack;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.d20charactersheet.framework.boc.model.AttackWield;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays a WeaponAttack to be created or edited.
 */
public abstract class WeaponAttackActivity extends FormActivity<WeaponAttack> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);

    GameSystem gameSystem;
    Character character;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.weaponattack;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        character = characterHolder.getValue().getCharacter();
    }


    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.weaponattack_name);
        setText(form.getWeapon().getName(), R.id.weaponattack_weapon);
        setAttackWield();
        setText(Integer.toString(form.getAttackBonusModifier()), R.id.weaponattack_attack_bonus_modifier);
        setText(Integer.toString(form.getDamageBonusModifier()), R.id.weaponattack_damage_bonus_modifier);
        setAmmunition();
        setText(form.getDescription(), R.id.weaponattack_desc);
    }

    private void setAttackWield() {
        final EnumSet<AttackWield> attackWieldSet = gameSystem.getRuleService().getAttackWields(form.getWeapon());
        final List<AttackWield> attackWields = new ArrayList<>(attackWieldSet);
        final SpinnerAdapter adapter = getAttackWieldAdapter(attackWields);
        final int position = getAttackWieldPosition(attackWields);
        setSpinner(R.id.weaponattack_attackwield, adapter, position);
    }

    private void setAmmunition() {
        setText(Integer.toString(form.getAmmunition()), R.id.weaponattack_ammo);
        final View tablerow = findViewById(R.id.weaponattack_ammunition_tablerow);
        if (WeaponCategory.PROJECTILE_WEAPON.equals(form.getWeapon().getWeaponCategory())) {
            tablerow.setVisibility(View.VISIBLE);
        } else {
            tablerow.setVisibility(View.GONE);
        }
    }

    private SpinnerAdapter getAttackWieldAdapter(final List<AttackWield> attackWields) {
        return new AttackWieldAdapter(this, displayService, attackWields);
    }

    private int getAttackWieldPosition(final List<AttackWield> attackWields) {
        for (int i = 0; i < attackWields.size(); i++) {
            if (attackWields.get(i).equals(form.getAttackWield())) {
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.weaponattack_name));
        form.setAttackWield((AttackWield) getSelectedItemOfSpinner(R.id.weaponattack_attackwield));
        form.setAttackBonusModifier(getIntegerOfTextView(R.id.weaponattack_attack_bonus_modifier));
        form.setDamageBonusModifier(getIntegerOfTextView(R.id.weaponattack_damage_bonus_modifier));
        form.setAmmunition(getIntegerOfTextView(R.id.weaponattack_ammo));
        form.setDescription(getTextOfTextView(R.id.weaponattack_desc));
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
        }
        return true;
    }

}
