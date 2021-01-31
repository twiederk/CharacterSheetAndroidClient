package com.android.ash.charactersheet.gui.sheet.combat;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.SheetPanelFactory;
import com.android.ash.charactersheet.gui.util.EditActivity;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Allow to edit the combat values of the character.
 */
public class CombatEditActivity extends EditActivity {

    private final Lazy<SheetPanelFactory> sheetPanelFactoryLazy = inject(SheetPanelFactory.class);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_combat_edit;
    }

    @Override
    protected int getHeading() {
        return R.string.combat_title;
    }

    @Override
    protected void setData() {
        sheetPanelFactoryLazy.getValue().createCombatEditPanel().setCombatEdit(findViewById(R.id.combat_edit_layout));
    }

    @Override
    protected void saveData() {
        sheetPanelFactoryLazy.getValue().createCombatEditPanel().saveData(findViewById(R.id.combat_edit_layout));
    }

}
