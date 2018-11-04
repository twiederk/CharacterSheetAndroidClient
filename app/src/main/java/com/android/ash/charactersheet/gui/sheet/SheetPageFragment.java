package com.android.ash.charactersheet.gui.sheet;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.sheet.appearance.AppearanceEditActivity;
import com.android.ash.charactersheet.gui.sheet.attribute.AttributeRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.attribute.AttributesEditActivity;
import com.android.ash.charactersheet.gui.sheet.combat.BaseAttackBonusRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.CombatEditActivity;
import com.android.ash.charactersheet.gui.sheet.combat.CombatManeuverBonusRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.CombatManeuverDefenceRollOnClickListener;
import com.android.ash.charactersheet.gui.sheet.combat.InitativeRollOnClickListener;
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

/**
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
public class SheetPageFragment extends PageFragment {

    private static final int RESULT_CODE_ADD_IMAGE = 100;
    private static final int RESULT_CODE_CROP_IMAGE = 200;
    private static final int RESULT_CODE_ADD_THUMBNAIL = 300;
    private static final int RESULT_CODE_CROP_THUMBNAIL = 400;
    private static final String COLON = ": ";

    private ImageHandler imageHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.page_sheet;
    }

    @Override
    protected void doCreateView() {
        imageHandler = new ImageHandler(getActivity());

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
        final Button button = (Button) view.findViewById(resourceId);
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
        final Button button = (Button) view.findViewById(resourceId);
        button.setOnClickListener(new SaveRollOnClickListener(character, save, displayService, ruleService,
                getDieRollView()));
    }

    private void setCombatOnClickListener() {
        final View combatView = view.findViewById(R.id.combat_include);
        combatView.setOnClickListener(new IntentOnClickListener(new Intent(getActivity(), CombatEditActivity.class)));

        final Button initativeButton = (Button) view.findViewById(R.id.combat_initiative);
        initativeButton.setOnClickListener(new InitativeRollOnClickListener(character, displayService, ruleService,
                getDieRollView()));

        final Button baseAttackBonusButton = (Button) view.findViewById(R.id.combat_baseattackbonus);
        baseAttackBonusButton.setOnClickListener(new BaseAttackBonusRollOnClickListener(character, displayService,
                ruleService, getDieRollView()));

        final Button cmbButton = (Button) view.findViewById(R.id.combat_cmb);
        cmbButton.setOnClickListener(new CombatManeuverBonusRollOnClickListener(character, displayService, ruleService,
                getDieRollView()));

        final Button cmdButton = (Button) view.findViewById(R.id.combat_cmd);
        cmdButton.setOnClickListener(new CombatManeuverDefenceRollOnClickListener(character, displayService,
                ruleService, getDieRollView()));

    }

    private DieRollView getDieRollView() {
        final DieRollView dieRollView = (DieRollView) view.findViewById(R.id.die_roll_view);
        return dieRollView;
    }

    private void setAppearance() {
        // player
        final TextView playerTextView = (TextView) view.findViewById(R.id.appearance_player);
        playerTextView.setText(character.getPlayer());

        // race
        final TextView raceTextView = (TextView) view.findViewById(R.id.appearance_race);
        final String race = character.getRace().getName();
        raceTextView.setText(race);

        // sex
        final TextView sexTextView = (TextView) view.findViewById(R.id.appearance_sex);
        final String sex = displayService.getDisplaySex(character.getSex());
        sexTextView.setText(sex);

        // class + level
        final TextView classLevelTextView = (TextView) view.findViewById(R.id.appearance_class_level);
        classLevelTextView.setText(getClassLevelText());

        // alignment
        final TextView alignmentTextView = (TextView) view.findViewById(R.id.appearance_alignment);
        final String alignment = displayService.getDisplayAlignment(character.getAlignment());
        alignmentTextView.setText(alignment);

        // experience + next level at
        final TextView experienceTextView = (TextView) view.findViewById(R.id.appearance_experience);
        experienceTextView.setText(character.getExperiencePoints() + " / "
                + xpService.getNextLevelAt(character.getXpTable(), character.getCharacterLevel()));
        checkExperiencePoints();
        setExperienceProgressBar();
    }

    private String getClassLevelText() {
        final StringBuilder classLevelText = new StringBuilder();
        for (final Iterator<ClassLevel> iterator = character.getClassLevels().iterator(); iterator.hasNext();) {
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
        final TextView experienceLabelTextView = (TextView) view.findViewById(R.id.appearance_experience_label);
        final ProgressBar experienceProgressBar = (ProgressBar) view.findViewById(R.id.appearance_experience_progress);
        experienceLabelTextView.setBackgroundColor(color);
        experienceProgressBar.setBackgroundColor(color);
    }

    private void setExperienceProgressBar() {
        final ProgressBar experienceProgressBar = (ProgressBar) view.findViewById(R.id.appearance_experience_progress);
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
        final TextView abilitiyTextView = (TextView) view.findViewById(idAttributeValue);
        abilitiyTextView.setText(Integer.toString(attributeValue));

        // modifier
        final String modifier = displayService.getDisplayModifier(ruleService.getModifier(attributeValue));
        final TextView modifierTextView = (TextView) view.findViewById(idAttributeMod);
        modifierTextView.setText(modifier);
    }

    private void setCombat() {
        // hit points
        final TextView hitPointsTextView = (TextView) view.findViewById(R.id.combat_hitpoints);
        hitPointsTextView.setText(character.getHitPoints() + " (" + character.getMaxHitPoints() + ")");
        setHitPointsProgressBar();

        // armor class
        final TextView armorClassTextView = (TextView) view.findViewById(R.id.combat_armorclass);
        armorClassTextView.setText(Integer.toString(ruleService.getArmorClass(character)));

        // speed
        final TextView speedTextView = (TextView) view.findViewById(R.id.combat_speed);
        speedTextView.setText(Integer.toString(ruleService.getSpeed(character)));

        // initiative
        final TextView initiativeTextView = (TextView) view.findViewById(R.id.combat_initiative);
        initiativeTextView.setText(displayService.getDisplayModifier(ruleService.getInitative(character)));

        // base attack bonus
        final TextView baseAttackBonusTextView = (TextView) view.findViewById(R.id.combat_baseattackbonus);
        baseAttackBonusTextView.setText(displayService.getDisplayModifier(ruleService.getBaseAttackBonus(character)));

        // cmb
        final TextView cmbTextView = (TextView) view.findViewById(R.id.combat_cmb);
        cmbTextView.setText(displayService.getDisplayModifier(ruleService.getCombatManeuverBonus(character)));

        // cmd
        final TextView cmdTextView = (TextView) view.findViewById(R.id.combat_cmd);
        cmdTextView.setText(displayService.getDisplayModifier(ruleService.getCombatManeuverDefence(character)));

    }

    private void setHitPointsProgressBar() {
        final ProgressBar hitPointsProgressBar = (ProgressBar) view.findViewById(R.id.combat_hitpoints_progress);
        hitPointsProgressBar.setMax(character.getMaxHitPoints());
        hitPointsProgressBar.setProgress(character.getHitPoints());
    }

    private void setSave() {
        // fortitude
        final TextView fortitudeTextView = (TextView) view.findViewById(R.id.save_fortitude);
        int savingThrow = ruleService.getSave(character, Save.FORTITUDE);
        fortitudeTextView.setText(displayService.getDisplayModifier(savingThrow));

        // reflex
        final TextView reflexTextView = (TextView) view.findViewById(R.id.save_reflex);
        savingThrow = ruleService.getSave(character, Save.REFLEX);
        reflexTextView.setText(displayService.getDisplayModifier(savingThrow));

        // will
        final TextView willTextView = (TextView) view.findViewById(R.id.save_will);
        savingThrow = ruleService.getSave(character, Save.WILL);
        willTextView.setText(displayService.getDisplayModifier(savingThrow));
    }

    private void setMoney() {
        final TextView goldTextView = (TextView) view.findViewById(R.id.money_gold);
        goldTextView.setText(getGold());
    }

    private String getGold() {
        final float gold = ruleService.getGold(character);
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        return decimalFormat.format(gold);
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_page_sheet, menu);
    }

    /**
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_page_sheet_add_image:
            jumpToImageGallery(RESULT_CODE_ADD_IMAGE);
            break;

        case R.id.menu_page_sheet_add_thumbnail:
            jumpToImageGallery(RESULT_CODE_ADD_THUMBNAIL);
            break;

        default:
            throw new IllegalStateException("Selected menu item is unknown (" + item.getItemId() + ")");
        }
        return super.onOptionsItemSelected(item);
    }

    private void jumpToImageGallery(final int resultCode) {
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, resultCode);
    }

    private void jumpToCropImage(final Intent intent, final int resultCode) {
        startActivityForResult(intent, resultCode);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {
        switch (requestCode) {
        case RESULT_CODE_ADD_IMAGE:
            if (resultCode != Activity.RESULT_CANCELED) {
                final Uri selectedImage = resultIntent.getData();
                final Intent intent = imageHandler.configureCropImage(selectedImage);
                try {
                    jumpToCropImage(intent, RESULT_CODE_CROP_IMAGE);
                } catch (final Exception exception) {
                    Logger.warn("Failed to crop image", exception);
                    final String filename = imageHandler.getFilename(resultIntent);
                    addImage(filename);
                    preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);
                    setActivityBackground();
                }
            }
            break;

        case RESULT_CODE_CROP_IMAGE:
            if (resultCode != Activity.RESULT_CANCELED) {
                final String filename = imageHandler.getFilename(resultIntent);
                addImage(filename);
                preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);
                setActivityBackground();
            }
            break;

        case RESULT_CODE_ADD_THUMBNAIL:
            if (resultCode != Activity.RESULT_CANCELED) {
                final Uri selectedThumbnail = resultIntent.getData();
                final Intent intent = imageHandler.configureCropThumbnail(selectedThumbnail);
                try {
                    jumpToCropImage(intent, RESULT_CODE_CROP_THUMBNAIL);
                } catch (final Exception exception) {
                    Logger.warn("Failed to crop image", exception);
                    final String filename = imageHandler.getFilename(resultIntent);
                    addThumbnail(filename);
                }
            }
            break;

        case RESULT_CODE_CROP_THUMBNAIL:
            if (resultCode != Activity.RESULT_CANCELED) {
                final String filename = imageHandler.getFilename(resultIntent);
                addThumbnail(filename);
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

    private void addImage(final String filename) {
        Logger.debug("filename: " + filename);
        try {
            final int oldImageId = character.getImageId();
            final int newImageId = imageService.createImage(filename);
            character.setImageId(newImageId);
            characterService.updateCharacter(character);
            imageService.deleteImage(oldImageId);
        } catch (final Exception exception) {
            Logger.error("Failed to add image", exception);
            final StringBuilder message = new StringBuilder();
            message.append(getString(R.string.page_sheet_message_add_image_failed));
            message.append(COLON);
            message.append(exception.getMessage());
            Toast.makeText(getActivity(), message.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void addThumbnail(final String filename) {
        Logger.debug("filename: " + filename);
        try {
            final int oldImageId = character.getThumbImageId();
            final int newImageId = imageService.createImage(filename);
            character.setThumbImageId(newImageId);
            characterService.updateCharacter(character);
            imageService.deleteImage(oldImageId);
        } catch (final Exception exception) {
            Logger.error("Failed to add thumbnail", exception);
            final StringBuilder message = new StringBuilder();
            message.append(getString(R.string.page_sheet_message_add_thumbnail_failed));
            message.append(COLON);
            message.append(exception.getMessage());
            Toast.makeText(getActivity(), message.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(character.getName());
        setActivityBackground();
        setAppearance();
        setAttribute();
        setCombat();
        setSave();
        setMoney();
    }

}
