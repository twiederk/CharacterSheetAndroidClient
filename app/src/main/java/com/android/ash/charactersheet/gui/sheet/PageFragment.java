package com.android.ash.charactersheet.gui.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.util.LogFragment;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.FeatService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.d20charactersheet.framework.boc.service.XpService;

/**
 * Base class for fragments which are part of the character sheet.
 */
public abstract class PageFragment extends LogFragment {

    protected GameSystem gameSystem;
    protected CharacterService characterService;
    protected RuleService ruleService;
    protected AndroidImageService imageService;
    protected DisplayService displayService;
    protected PreferenceService preferenceService;
    protected XpService xpService;
    protected FeatService featService;
    protected Character character;

    protected View view;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final CharacterSheetApplication application = (CharacterSheetApplication) getActivity().getApplication();
        gameSystem = application.getGameSystem();
        characterService = gameSystem.getCharacterService();
        ruleService = gameSystem.getRuleService();
        displayService = gameSystem.getDisplayService();
        imageService = (AndroidImageService) gameSystem.getImageService();
        preferenceService = application.getPreferenceService();
        xpService = gameSystem.getXpService();
        featService = gameSystem.getFeatService();
        character = application.getCharacter();

        setHasOptionsMenu(true);

        view = inflater.inflate(getLayoutId(), container, false);
        doCreateView();
        return view;
    }

    /**
     * Adds a tab to the tabhost of the fragment.
     * 
     * @param labelId
     *            The resource id of the tab label.
     * @param layoutId
     *            The resource id of XML layout of the tab.
     * @param iconId
     *            The resource id of the icon of the tab.
     */
    public void addTab(final int labelId, final int layoutId, final int iconId) {
        final TabHost tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        final String label = (String) getActivity().getResources().getText(labelId);
        final TabSpec tabSpec = tabHost.newTabSpec(label);
        tabSpec.setIndicator(label, getActivity().getResources().getDrawable(iconId));
        tabSpec.setContent(layoutId);
        tabHost.addTab(tabSpec);
    }

    protected abstract int getLayoutId();

    protected abstract void doCreateView();

}
