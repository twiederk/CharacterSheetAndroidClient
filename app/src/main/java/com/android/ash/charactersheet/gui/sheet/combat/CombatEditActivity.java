package com.android.ash.charactersheet.gui.sheet.combat;

import android.os.Bundle;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.EditActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.android.ash.charactersheet.gui.widget.numberview.SumNumberView;

/**
 * Allow to edit the combat values of the character.
 */
public class CombatEditActivity extends EditActivity {

    private SumNumberView hitPointsQuickNumberView;
    private SumNumberView maxHitPointsNumberView;
    private StepNumberView armorClassNumberView;
    private TextView armorClassFormularTextView;
    private StepNumberView initiativeNumberView;
    private TextView initiativeFormularTextView;
    private StepNumberView cmbNumberView;
    private TextView cmbFormularTextView;
    private StepNumberView cmdNumberView;
    private TextView cmdFormularTextView;

    private NumberViewController hitPointsController;
    private NumberViewController maxHitPointsController;
    private NumberViewController armorClassController;
    private NumberViewController initiativeModifierController;
    private NumberViewController cmbController;
    private NumberViewController cmdController;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.activity_combat_edit);
        setTitle(R.string.combat_title);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setData() {
        findViews();
        hitPointsController = new SimpleNumberViewController(character.getHitPoints());
        hitPointsQuickNumberView.setController(hitPointsController);

        maxHitPointsController = new PositiveNumberViewController(character.getMaxHitPoints());
        maxHitPointsNumberView.setController(maxHitPointsController);

        armorClassController = new SimpleNumberViewController(character.getArmorClass());
        armorClassNumberView.setController(armorClassController);
        final int dexterityModifier = ruleService.getModifier(character.getDexterity());
        armorClassFormularTextView.setText(displayService.getDisplayArmourClassFormular(dexterityModifier));

        initiativeModifierController = new SimpleNumberViewController(character.getInitiativeModifier());
        initiativeNumberView.setController(initiativeModifierController);
        initiativeFormularTextView.setText(displayService.getDisplaySimpleFormular(dexterityModifier));

        cmbController = new SimpleNumberViewController(character.getCmbModifier());
        cmbNumberView.setController(cmbController);
        final int cmb = ruleService.getCombatManeuverBonus(character) - character.getCmbModifier();
        cmbFormularTextView.setText(displayService.getDisplaySimpleFormular(cmb));

        cmdController = new SimpleNumberViewController(character.getCmdModifier());
        cmdNumberView.setController(cmdController);
        final int cmd = ruleService.getCombatManeuverDefence(character)- character.getCmdModifier();
        cmdFormularTextView.setText(displayService.getDisplaySimpleFormular(cmd));

    }

    private void findViews() {
        hitPointsQuickNumberView = (SumNumberView) findViewById(R.id.combat_hitpoints);
        maxHitPointsNumberView = (SumNumberView) findViewById(R.id.combat_max_hitpoints);
        armorClassNumberView = (StepNumberView) findViewById(R.id.combat_armorclass);
        armorClassFormularTextView = (TextView) findViewById(R.id.combat_armorclass_formular);
        initiativeNumberView = (StepNumberView) findViewById(R.id.combat_initiative);
        initiativeFormularTextView = (TextView) findViewById(R.id.combat_initative_formular);
        cmbNumberView = (StepNumberView) findViewById(R.id.combat_cmb);
        cmbFormularTextView = (TextView) findViewById(R.id.combat_cmb_formular);
        cmdNumberView = (StepNumberView) findViewById(R.id.combat_cmd);
        cmdFormularTextView = (TextView) findViewById(R.id.combat_cmd_formular);
    }

    @Override
    protected void saveData() {
        character.setHitPoints((Integer) hitPointsController.getNumber());
        character.setMaxHitPoints((Integer) maxHitPointsController.getNumber());
        character.setArmorClass((Integer) armorClassController.getNumber());
        character.setInitiativeModifier((Integer) initiativeModifierController.getNumber());
        character.setCmbModifier((Integer) cmbController.getNumber());
        character.setCmdModifier((Integer) cmdController.getNumber());
    }
}
