package com.android.ash.charactersheet.gui.sheet.clazz;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.MessageManager;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display class levels in a list view. Displays the class, the level with a NumberView and a buttoon to
 * delete the class. If the last class level is to be deleted a message is shown.
 */
public class ClassLevelArrayAdapter extends DisplayArrayAdapter<ClassLevel> {

    private final MessageManager messageManager;

    /**
     * Creates the adapter.
     * 
     * @param context
     *            The context on the Activity.
     * @param displayService
     *            The display service to display class name.
     * @param itemViewResourceId
     *            The id of the layout resource.
     * @param classLevels
     *            The class levels to display.
     */
    public ClassLevelArrayAdapter(final Context context, final DisplayService displayService,
            final int itemViewResourceId, final List<ClassLevel> classLevels) {
        super(context, displayService, itemViewResourceId, classLevels);
        messageManager = new MessageManager(context, displayService);
    }

    @Override
    protected void fillView(final View view, final ClassLevel classLevel) {
        final TextView classTextView = (TextView) view.findViewById(R.id.class_name);
        final StepNumberView classLevelNumberView = (StepNumberView) view.findViewById(R.id.class_level);
        final Button deleteButton = (Button) view.findViewById(R.id.class_level_delete);

        final CharacterClass characterClass = classLevel.getCharacterClass();
        classTextView.setText(characterClass.getName());
        classLevelNumberView.setController(new NumberViewClassLevelController(classLevel));
        deleteButton.setOnClickListener(new DeleteLevelOnClickListener(this, classLevel, messageManager));
    }

}
