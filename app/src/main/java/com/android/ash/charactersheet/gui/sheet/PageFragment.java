package com.android.ash.charactersheet.gui.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.PreferenceServiceHolder;
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
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Base class for fragments which are part of the character sheet.
 */
public abstract class PageFragment extends LogFragment {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<PreferenceServiceHolder> preferenceServiceHolder = inject(PreferenceServiceHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);
    final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    GameSystem gameSystem;
    CharacterService characterService;
    RuleService ruleService;
    AndroidImageService imageService;
    DisplayService displayService;
    PreferenceService preferenceService;
    XpService xpService;
    FeatService featService;
    Character character;

    View view;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        characterService = Objects.requireNonNull(gameSystem).getCharacterService();
        ruleService = gameSystem.getRuleService();
        displayService = gameSystem.getDisplayService();
        imageService = (AndroidImageService) gameSystem.getImageService();
        preferenceService = preferenceServiceHolder.getValue().getPreferenceService();
        xpService = gameSystem.getXpService();
        featService = gameSystem.getFeatService();
        character = characterHolder.getValue().getCharacter();

        setHasOptionsMenu(true);

        view = inflater.inflate(getLayoutId(), container, false);
        doCreateView();
        return view;
    }

    /**
     * Adds a tab to the tab host of the fragment.
     *
     * @param labelId  The resource id of the tab label.
     * @param layoutId The resource id of XML layout of the tab.
     * @param iconId   The resource id of the icon of the tab.
     */
    void addTab(final int labelId, final int layoutId, final int iconId) {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        final String label = (String) requireActivity().getResources().getText(labelId);
        final TabSpec tabSpec = tabHost.newTabSpec(label);
        tabSpec.setIndicator(label, ContextCompat.getDrawable(requireActivity(), iconId));
        tabSpec.setContent(layoutId);
        tabHost.addTab(tabSpec);
    }

    protected abstract int getLayoutId();

    protected abstract void doCreateView();

}
