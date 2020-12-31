package com.android.ash.charactersheet.gui.sheet;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.sheet.appearance.AppearanceEditActivity;
import com.android.ash.charactersheet.gui.sheet.attribute.AttributeRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.attribute.AttributesEditActivity;
import com.android.ash.charactersheet.gui.sheet.combat.BaseAttackBonusRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.CombatEditActivity;
import com.android.ash.charactersheet.gui.sheet.combat.CombatManeuverBonusRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.CombatManeuverDefenceRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.InitiativeRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.money.MoneyEditActivity;
import com.android.ash.charactersheet.gui.sheet.save.SaveEditActivity;
import com.android.ash.charactersheet.gui.sheet.save.SaveRollOnClickListener;
import com.android.ash.charactersheet.gui.util.HideOnClickListener;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.service.XpService;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

/**
 * Displays the characters appearance, abilities, saves, money and combat values.
 */
public class SheetPageFragment extends PageFragment {

    private static final int RESULT_CODE_ADD_IMAGE = 100;
    private static final int RESULT_CODE_ADD_THUMBNAIL = 300;
    private static final String COLON = ": ";

    @Override
    protected int getLayoutId() {
        return R.layout.page_sheet;
    }

    @Override
    protected void doCreateView() {
        setOnClickListener();
    }

    private void setOnClickListener() {
        final View appearanceView = view.findViewById(R.id.appearance_include);
        appearanceView.setOnClickListener(new IntentOnClickListener(new Intent(getActivity(),
                AppearanceEditActivity.class)));

        setAttributeOnClickListener();

        setCombatOnClickListener();

        setSaveOnClickListener();

        final View moneyView = view.findViewById(R.id.money_include);
        moneyView.setOnClickListener(new IntentOnClickListener(new Intent(getActivity(), MoneyEditActivity.class)));

        final DieRollView dieRollView = getDieRollView();
        dieRollView.setOnClickListener(new HideOnClickListener());

    }

    private void setAttributeOnClickListener() {
        final View attributeView = view.findViewById(R.id.attribute_include);
        attributeView.setOnClickListener(new IntentOnClickListener(new Intent(getActivity(),
                AttributesEditActivity.class)));

        setAttributeOnClickListener(R.id.attribute_str_mod, Attribute.STRENGTH);
        setAttributeOnClickListener(R.id.attribute_dex_mod, Attribute.DEXTERITY);
        setAttributeOnClickListener(R.id.attribute_con_mod, Attribute.CONSTITUTION);
        setAttributeOnClickListener(R.id.attribute_int_mod, Attribute.INTELLIGENCE);
        setAttributeOnClickListener(R.id.attribute_wis_mod, Attribute.WISDOM);
        setAttributeOnClickListener(R.id.attribute_cha_mod, Attribute.CHARISMA);
    }

    private void setAttributeOnClickListener(final int resourceId, final Attribute attribute) {
        final Button button = view.findViewById(resourceId);
        button.setOnClickListener(new AttributeRollOnClickListener(character, attribute, displayService, ruleService,
                getDieRollView()));
    }

    private void setSaveOnClickListener() {
        final View savingThrowView = view.findViewById(R.id.saving_throw_include);
        savingThrowView
                .setOnClickListener(new IntentOnClickListener(new Intent(getActivity(), SaveEditActivity.class)));

        setSaveOnClickListener(R.id.save_fortitude, Save.FORTITUDE);
        setSaveOnClickListener(R.id.save_reflex, Save.REFLEX);
        setSaveOnClickListener(R.id.save_will, Save.WILL);
    }

    private void setSaveOnClickListener(final int resourceId, final Save save) {
        final Button button = view.findViewById(resourceId);
        button.setOnClickListener(new SaveRollOnClickListener(character, save, displayService, ruleService,
                getDieRollView()));
    }

    private void setCombatOnClickListener() {
        final View combatView = view.findViewById(R.id.combat_include);
        combatView.setOnClickListener(new IntentOnClickListener(new Intent(getActivity(), CombatEditActivity.class)));

        final Button initiativeButton = view.findViewById(R.id.combat_initiative);
        initiativeButton.setOnClickListener(new InitiativeRollOnClickListener(character, displayService, ruleService,
                getDieRollView()));

        final Button baseAttackBonusButton = view.findViewById(R.id.combat_baseattackbonus);
        baseAttackBonusButton.setOnClickListener(new BaseAttackBonusRollOnClickListener(character, displayService,
                ruleService, getDieRollView()));

        final Button cmbButton = view.findViewById(R.id.combat_cmb);
        cmbButton.setOnClickListener(new CombatManeuverBonusRollOnClickListener(character, displayService, ruleService,
                getDieRollView()));

        final Button cmdButton = view.findViewById(R.id.combat_cmd);
        cmdButton.setOnClickListener(new CombatManeuverDefenceRollOnClickListener(character, displayService,
                ruleService, getDieRollView()));

    }

    private DieRollView getDieRollView() {
        return view.findViewById(R.id.die_roll_view);
    }

    private void setAppearance() {
        // player
        final TextView playerTextView = view.findViewById(R.id.appearance_player);
        playerTextView.setText(character.getPlayer());

        // race
        final TextView raceTextView = view.findViewById(R.id.appearance_race);
        final String race = character.getRace().getName();
        raceTextView.setText(race);

        // sex
        final TextView sexTextView = view.findViewById(R.id.appearance_sex);
        final String sex = displayService.getDisplaySex(character.getSex());
        sexTextView.setText(sex);

        // class + level
        final TextView classLevelTextView = view.findViewById(R.id.appearance_class_level);
        classLevelTextView.setText(getClassLevelText());

        // alignment
        final TextView alignmentTextView = view.findViewById(R.id.appearance_alignment);
        final String alignment = displayService.getDisplayAlignment(character.getAlignment());
        alignmentTextView.setText(alignment);

        // experience + next level at
        final TextView experienceTextView = view.findViewById(R.id.appearance_experience);
        experienceTextView.setText(buildExperiencePointsText());
        checkExperiencePoints();
        setExperienceProgressBar();
    }

    @NonNull
    private String buildExperiencePointsText() {
        return character.getExperiencePoints() + " / "
                + xpService.getNextLevelAt(character.getXpTable(), character.getCharacterLevel());
    }

    private String getClassLevelText() {
        final StringBuilder classLevelText = new StringBuilder();
        for (final Iterator<ClassLevel> iterator = character.getClassLevels().iterator(); iterator.hasNext(); ) {
            final ClassLevel classLevel = iterator.next();
            classLevelText.append(displayService.getDisplayClassLevel(classLevel));
            if (iterator.hasNext()) {
                classLevelText.append("\n");
            }
        }
        return classLevelText.toString();
    }

    private void checkExperiencePoints() {
        int color = android.R.color.transparent;
        final XpService xpService = gameSystem.getXpService();
        if (!xpService.isValidExperiencePointsToCharacterLevel(character.getExperiencePoints(), character)) {
            color = Color.RED;
        }
        final TextView experienceLabelTextView = view.findViewById(R.id.appearance_experience_label);
        final ProgressBar experienceProgressBar = view.findViewById(R.id.appearance_experience_progress);
        experienceLabelTextView.setBackgroundColor(color);
        experienceProgressBar.setBackgroundColor(color);
    }

    private void setExperienceProgressBar() {
        final ProgressBar experienceProgressBar = view.findViewById(R.id.appearance_experience_progress);
        final int nextLevelAt = xpService.getNextLevelAt(character.getXpTable(), character.getCharacterLevel());
        final int lastLevelAt = xpService.getNextLevelAt(character.getXpTable(), character.getCharacterLevel() - 1);
        experienceProgressBar.setMax(nextLevelAt - lastLevelAt);
        experienceProgressBar.setProgress(character.getExperiencePoints() - lastLevelAt);
    }

    private void setAttribute() {
        // strength
        displayAttribute(character.getStrength(), R.id.attribute_str_value, R.id.attribute_str_mod);

        // dexterity
        displayAttribute(character.getDexterity(), R.id.attribute_dex_value, R.id.attribute_dex_mod);

        // constitution
        displayAttribute(character.getConstitution(), R.id.attribute_con_value, R.id.attribute_con_mod);

        // intelligence
        displayAttribute(character.getIntelligence(), R.id.attribute_int_value, R.id.attribute_int_mod);

        // wisdom
        displayAttribute(character.getWisdom(), R.id.attribute_wis_value, R.id.attribute_wis_mod);

        // charisma
        displayAttribute(character.getCharisma(), R.id.attribute_cha_value, R.id.attribute_cha_mod);

    }

    private void displayAttribute(final int attributeValue, final int idAttributeValue, final int idAttributeMod) {
        // attribute
        final TextView abilityTextView = view.findViewById(idAttributeValue);
        abilityTextView.setText(String.format(Locale.US, "%d", attributeValue));

        // modifier
        final String modifier = displayService.getDisplayModifier(ruleService.getModifier(attributeValue));
        final TextView modifierTextView = view.findViewById(idAttributeMod);
        modifierTextView.setText(modifier);
    }

    private void setCombat() {
        // hit points
        final TextView hitPointsTextView = view.findViewById(R.id.combat_hitpoints);
        hitPointsTextView.setText(buildHitPointsText());
        setHitPointsProgressBar();

        // armor class
        final TextView armorClassTextView = view.findViewById(R.id.combat_armorclass);
        armorClassTextView.setText(String.format(Locale.US, "%d", ruleService.getArmorClass(character)));

        // flatfooted armor class
        final TextView flatFootedArmorClassTextView = view.findViewById(R.id.combat_flatfooted_armorclass);
        flatFootedArmorClassTextView.setText(String.format(Locale.US, "%d", ruleService.calculateFlatFootedArmorClass(character)));

        // touch armor class
        final TextView touchArmorClassTextView = view.findViewById(R.id.combat_touch_armorclass);
        touchArmorClassTextView.setText(String.format(Locale.US, "%d", ruleService.calculateTouchArmorClass(character)));

        // speed
        final TextView speedTextView = view.findViewById(R.id.combat_speed);
        speedTextView.setText(String.format(Locale.US, "%d", ruleService.getSpeed(character)));

        // initiative
        final TextView initiativeTextView = view.findViewById(R.id.combat_initiative);
        initiativeTextView.setText(displayService.getDisplayModifier(ruleService.getInitative(character)));

        // base attack bonus
        final TextView baseAttackBonusTextView = view.findViewById(R.id.combat_baseattackbonus);
        baseAttackBonusTextView.setText(displayService.getDisplayModifier(ruleService.getBaseAttackBonus(character)));

        // cmb
        final TextView cmbTextView = view.findViewById(R.id.combat_cmb);
        cmbTextView.setText(displayService.getDisplayModifier(ruleService.getCombatManeuverBonus(character)));

        // cmd
        final TextView cmdTextView = view.findViewById(R.id.combat_cmd);
        cmdTextView.setText(displayService.getDisplayModifier(ruleService.getCombatManeuverDefence(character)));

    }

    @NonNull
    private String buildHitPointsText() {
        return character.getHitPoints() + " (" + character.getMaxHitPoints() + ")";
    }

    private void setHitPointsProgressBar() {
        final ProgressBar hitPointsProgressBar = view.findViewById(R.id.combat_hitpoints_progress);
        hitPointsProgressBar.setMax(character.getMaxHitPoints());
        hitPointsProgressBar.setProgress(character.getHitPoints());
    }

    private void setSave() {
        // fortitude
        final TextView fortitudeTextView = view.findViewById(R.id.save_fortitude);
        int savingThrow = ruleService.getSave(character, Save.FORTITUDE);
        fortitudeTextView.setText(displayService.getDisplayModifier(savingThrow));

        // reflex
        final TextView reflexTextView = view.findViewById(R.id.save_reflex);
        savingThrow = ruleService.getSave(character, Save.REFLEX);
        reflexTextView.setText(displayService.getDisplayModifier(savingThrow));

        // will
        final TextView willTextView = view.findViewById(R.id.save_will);
        savingThrow = ruleService.getSave(character, Save.WILL);
        willTextView.setText(displayService.getDisplayModifier(savingThrow));
    }

    private void setMoney() {
        final TextView goldTextView = view.findViewById(R.id.money_gold);
        goldTextView.setText(getGold());
    }

    private String getGold() {
        final float gold = ruleService.getGold(character);
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        return decimalFormat.format(gold);
    }

    private void setCharacterName() {
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(character.getName());
        }
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_page_sheet, menu);
    }

    /**
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_page_sheet_add_image) {
            jumpToImageGallery(RESULT_CODE_ADD_IMAGE);
        } else if (itemId == R.id.menu_page_sheet_add_thumbnail) {
            jumpToImageGallery(RESULT_CODE_ADD_THUMBNAIL);
        } else {
            throw new IllegalStateException("Selected menu item is unknown (" + item.getItemId() + ")");
        }
        return super.onOptionsItemSelected(item);
    }

    private void jumpToImageGallery(final int resultCode) {
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, resultCode);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {
        switch (requestCode) {
            case RESULT_CODE_ADD_IMAGE:
                if (resultCode != Activity.RESULT_CANCELED) {
                    final Uri selectedImage = resultIntent.getData();
                    addImage(selectedImage);
                    preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);
                    setActivityBackground();
                }
                break;

            case RESULT_CODE_ADD_THUMBNAIL:
                if (resultCode != Activity.RESULT_CANCELED) {
                    final Uri selectedThumbnail = resultIntent.getData();
                    addThumbnail(selectedThumbnail);
                }
                break;

            default:
                throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }

    }

    private void setActivityBackground() {
        if (getActivity() instanceof CharacterSheetActivity) {
            final CharacterSheetActivity characterSheetActivity = (CharacterSheetActivity) getActivity();
            characterSheetActivity.setBackground();
        }

    }

    private void addImage(final Uri uri) {
        Logger.debug("uri: " + uri);
        try {
            final int oldImageId = character.getImageId();
            InputStream inputStream = getInputStream(uri);
            final int newImageId = imageService.createImage(inputStream);
            character.setImageId(newImageId);
            characterService.updateCharacter(character);
            imageService.deleteImage(oldImageId);
            firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.IMAGE_ADD, null);
        } catch (final Exception exception) {
            Logger.error("Failed to add image", exception);
            String message = getString(R.string.page_sheet_message_add_image_failed) + COLON + exception.getMessage();
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

    private InputStream getInputStream(Uri uri) throws FileNotFoundException {
        ContentResolver contentResolver = requireActivity().getContentResolver();
        return contentResolver.openInputStream(uri);
    }

    private void addThumbnail(final Uri uri) {
        Logger.debug("uri: " + uri);
        try {
            final int oldImageId = character.getThumbImageId();
            InputStream inputStream = getInputStream(uri);
            final int newImageId = imageService.createImage(inputStream);
            character.setThumbImageId(newImageId);
            characterService.updateCharacter(character);
            imageService.deleteImage(oldImageId);
            firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.THUMBNAIL_ADD, null);
        } catch (final Exception exception) {
            Logger.error("Failed to add thumbnail", exception);
            String message = getString(R.string.page_sheet_message_add_thumbnail_failed) + COLON + exception.getMessage();
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.SHEET);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "SheetPageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        setCharacterName();
        setActivityBackground();
        setAppearance();
        setAttribute();
        setCombat();
        setSave();
        setMoney();
    }

}
