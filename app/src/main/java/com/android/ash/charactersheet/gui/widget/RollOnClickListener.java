package com.android.ash.charactersheet.gui.widget;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.dierollview.DefaultDieRollViewController;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollViewController;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.google.firebase.analytics.FirebaseAnalytics;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;


/**
 * Base class for die roll listeners. Displays result of die roll. Subclasses execute the die roll.
 */
public abstract class RollOnClickListener implements OnClickListener {

    private static final String SPACE = " ";

    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    protected final Character character;
    protected final DisplayService displayService;
    protected final RuleService ruleService;
    private final DieRollView dieRollView;

    /**
     * Instantiates RollOnClickListener.
     *
     * @param character      The character to roll die.
     * @param displayService The service to display data.
     * @param ruleService    The service to execute the roll.
     * @param dieRollView    The view to display the result.
     */
    protected RollOnClickListener(final Character character, final DisplayService displayService,
                                  final RuleService ruleService, final DieRollView dieRollView) {
        this.character = character;
        this.displayService = displayService;
        this.ruleService = ruleService;
        this.dieRollView = dieRollView;
    }

    @Override
    public void onClick(final View view) {
        final String title = getTitle(view);
        final DieRoll dieRoll = getDieRoll();
        final DieRollViewController controller = new DefaultDieRollViewController(title, dieRoll);
        dieRollView.setController(controller);
        dieRollView.setVisibility(View.VISIBLE);
        logEventDieRoll(title);
    }

    private String getTitle(final View view) {
        return getTitle(view.getResources()) + SPACE + view.getResources().getString(R.string.page_sheet_die_roll_title);
    }

    private void logEventDieRoll(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.DIE_ROLL_NAME, title);
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.DIE_ROLL, bundle);
    }

    protected abstract String getTitle(Resources resources);

    protected abstract DieRoll getDieRoll();

}
