package com.android.ash.charactersheet.gui.sheet.save;

import android.os.Bundle;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.EditActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.Save;

/**
 * Displays fortitude, reflex and will saving throw. Displaying their base save, attribute modifier and modifier. The
 * modifier can be changed by using a NumberView. The Changes can be saved or cancelled.
 */
public class SaveEditActivity extends EditActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.activity_save_edit);
        setTitle(R.string.save_title);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setData() {
        setSave(Save.FORTITUDE, R.id.save_fortitude_base, R.id.save_fortitude_attribute, R.id.save_fortitude_modifier);
        setSave(Save.REFLEX, R.id.save_reflex_base, R.id.save_reflex_attribute, R.id.save_reflex_modifier);
        setSave(Save.WILL, R.id.save_will_base, R.id.save_will_attribute, R.id.save_will_modifier);
    }

    private void setSave(final Save save, final int baseResourceId, final int attributeResourceId,
            final int modifierResourceId) {
        final int baseSave = ruleService.getBaseSave(character, save);
        final TextView baseTextView = findViewById(baseResourceId);
        baseTextView.setText(displayService.getDisplayModifier(baseSave));

        final int attributeModifier = ruleService.getSaveAttributeModifier(character, save);
        final TextView attributeTextView = findViewById(attributeResourceId);
        attributeTextView.setText(displayService.getDisplayModifier(attributeModifier));

        final int modifier = ruleService.getSaveModifier(character, save);
        final StepNumberView modifierNumberView = findViewById(modifierResourceId);
        modifierNumberView.setController(new SimpleNumberViewController(modifier));
    }

    @Override
    protected void saveData() {
        character.setFortitudeModifier(getModifier(R.id.save_fortitude_modifier));
        character.setReflexModifier(getModifier(R.id.save_reflex_modifier));
        character.setWillModifier(getModifier(R.id.save_will_modifier));
    }

    private int getModifier(final int modifierResourceId) {
        final StepNumberView modifierNumberView = findViewById(modifierResourceId);
        final NumberViewController controller = modifierNumberView.getController();
        return (int) (Integer) controller.getNumber();
    }

}
