package com.android.ash.charactersheet.gui.sheet.save;

import static org.koin.java.KoinJavaComponent.inject;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.SheetPanelFactory;
import com.android.ash.charactersheet.gui.util.EditActivity;

import kotlin.Lazy;

/**
 * Displays saves. Displaying their base save, attribute modifier and modifier. The
 * modifier can be changed by using a NumberView. The Changes can be saved or cancelled.
 */
public class SaveEditActivity extends EditActivity {

    private final Lazy<SheetPanelFactory> sheetPanelFactoryLazy = inject(SheetPanelFactory.class);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_save_edit;
    }

    @Override
    protected int getHeading() {
        return R.string.save_title;
    }

    @Override
    protected void setData() {
        sheetPanelFactoryLazy.getValue().createSaveEditPanel().setData(findViewById(android.R.id.content).getRootView());
    }

    @Override
    protected void saveData() {
        sheetPanelFactoryLazy.getValue().createSaveEditPanel().saveData(findViewById(android.R.id.content).getRootView());
    }

}
