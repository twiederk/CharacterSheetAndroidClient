package com.android.ash.charactersheet.gui.sheet.attack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.ammunitionview.AmmunitionView;
import com.android.ash.charactersheet.gui.widget.ammunitionview.AmmunitionViewController;
import com.android.ash.charactersheet.gui.widget.attackbonusview.AttackBonusView;
import com.android.ash.charactersheet.gui.widget.attackbonusview.AttackOnClickListener;
import com.android.ash.charactersheet.gui.widget.attackbonusview.DefaultAttackBonusViewController;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays a weaopn attack. The name is displayes in upper left corner. In the upper right corner the attack bonuses
 * are displayed on buttons. In the second row from right to left is the following displayed. Normal damage, critical
 * damage, range increment for range weapons, ammunition for projectile weapons.
 */
public class WeaponAttackListAdapter extends DisplayArrayAdapter<WeaponAttack> {

    private AttackOnClickListener attackOnClickListener;
    private DamageOnClickListener damageOnClickListener;
    private CriticalOnClickListener criticalOnClickListener;

    /**
     * Instantiates WeaponAttackListAdapter
     * 
     * @param context
     *            The context of the adapter.
     * @param displayService
     *            The service to display data.
     * @param itemViewResourceId
     *            The id of the layout resource.
     * @param weaponAttacks
     *            The weapon attacks to display.
     */
    public WeaponAttackListAdapter(final Context context, final DisplayService displayService,
            final int itemViewResourceId, final List<WeaponAttack> weaponAttacks) {
        super(context, displayService, itemViewResourceId, weaponAttacks);
    }

    @Override
    protected void fillView(final View view, final WeaponAttack weaponAttack) {
        final AmmunitionViewController ammunitionViewController = new AmmunitionViewController(weaponAttack);

        final Intent intent = new Intent(getContext(), WeaponAttackEditActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, weaponAttack.getId());
        final OnClickListener onClickListener = new IntentOnClickListener(intent);

        setName(view, weaponAttack, onClickListener);
        setAttackBonus(view, weaponAttack, ammunitionViewController);
        setDamage(view, weaponAttack);
        setCritical(view, weaponAttack);
        setRangeIncrement(view, weaponAttack);
        setAmmunition(view, weaponAttack, ammunitionViewController);
        setDescription(view, weaponAttack, onClickListener);

    }

    private void setName(final View view, final WeaponAttack weaponAttack, final OnClickListener onClickListener) {
        final TextView nameTextView = view.findViewById(R.id.weaponattack_name);
        nameTextView.setText(weaponAttack.getName());
        nameTextView.setOnClickListener(onClickListener);
        nameTextView.setOnLongClickListener(view1 -> false);
    }

    private void setAttackBonus(final View view, final WeaponAttack weaponAttack,
            final AmmunitionViewController ammunitionViewController) {
        final AttackBonusView attackBonusView = view.findViewById(R.id.weaponattack_attackbonus);
        attackBonusView.setController(new DefaultAttackBonusViewController(weaponAttack), ammunitionViewController);
        attackBonusView.setAttackOnClickListener(attackOnClickListener);
    }

    private void setDamage(final View view, final WeaponAttack weaponAttack) {
        final Button damageButton = view.findViewById(R.id.weaponattack_damage);
        damageButton.setText(displayService.getDisplayDamage(weaponAttack));
        damageButton.setOnClickListener(view1 -> damageOnClickListener.onClick(view1, weaponAttack));
    }

    private void setCritical(final View view, final WeaponAttack weaponAttack) {
        final Button criticalButton = view.findViewById(R.id.weaponattack_critical);
        criticalButton.setText(displayService.getDisplayCritical(weaponAttack.getCritical()));
        criticalButton.setOnClickListener(view1 -> criticalOnClickListener.onClick(view1, weaponAttack));
    }

    private void setRangeIncrement(final View view, final WeaponAttack weaponAttack) {
        final TextView rangeIncrementTextView = view.findViewById(R.id.weaponattack_range_increment);
        final int rangeIncrement = weaponAttack.getWeapon().getRangeIncrement();
        if (rangeIncrement == 0) {
            rangeIncrementTextView.setVisibility(View.GONE);
        } else {
            rangeIncrementTextView.setVisibility(View.VISIBLE);
            rangeIncrementTextView.setText(displayService.getDisplayRangeIncrement(rangeIncrement));
        }
    }

    private void setAmmunition(final View view, final WeaponAttack weaponAttack,
            final AmmunitionViewController ammunitionViewController) {
        final AmmunitionView ammunitionView = view.findViewById(R.id.weaponattack_ammunition);
        ammunitionView.setController(ammunitionViewController);
        if (WeaponCategory.PROJECTILE_WEAPON.equals(weaponAttack.getWeapon().getWeaponCategory())) {
            ammunitionView.setVisibility(View.VISIBLE);
        } else {
            ammunitionView.setVisibility(View.GONE);
        }
    }

    private void setDescription(final View view, final WeaponAttack weaponAttack, final OnClickListener onClickListener) {
        final String description = weaponAttack.getDescription();
        final TextView descriptionTextView = view.findViewById(R.id.weaponattack_description);
        if (description.trim().length() == 0) {
            descriptionTextView.setVisibility(View.GONE);
        } else {
            descriptionTextView.setVisibility(View.VISIBLE);
            descriptionTextView.setText(description);
            descriptionTextView.setOnClickListener(onClickListener);
        }
    }

    /**
     * Sets a listener for attack rolls.
     * 
     * @param attackOnClickListener
     *            The listener for attack rolls.
     */
    public void setAttackOnClickListener(final AttackOnClickListener attackOnClickListener) {
        this.attackOnClickListener = attackOnClickListener;
    }

    /**
     * Sets a listener for damage rolls.
     * 
     * @param damageOnClickListener
     *            The listener for damage rolls.
     */
    public void setDamageOnClickListener(final DamageOnClickListener damageOnClickListener) {
        this.damageOnClickListener = damageOnClickListener;
    }

    /**
     * Sets a listener for critical damage rolls.
     * 
     * @param criticalOnClickListener
     *            The listene for critical damage rolls.
     */
    public void setCriticalOnClickListener(final CriticalOnClickListener criticalOnClickListener) {
        this.criticalOnClickListener = criticalOnClickListener;
    }
}
