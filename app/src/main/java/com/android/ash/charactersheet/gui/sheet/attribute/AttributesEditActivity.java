package com.android.ash.charactersheet.gui.sheet.attribute;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.EditActivity;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.android.ash.charactersheet.gui.widget.observertextview.ObserverTextView;

/**
 * Activity to edit attribute values of character.
 */
public class AttributesEditActivity extends EditActivity {

    private StepNumberView strengthNumberView;
    private StepNumberView dexterityNumberView;
    private StepNumberView constitutionNumberView;
    private StepNumberView intelligenceNumberView;
    private StepNumberView wisdomNumberView;
    private StepNumberView charismaNumberView;

    private AttributeModel strengthModel;
    private AttributeModel dexterityModel;
    private AttributeModel constitutionModel;
    private AttributeModel intelligenceModel;
    private AttributeModel wisdomModel;
    private AttributeModel charismaModel;

    private ObserverTextView strengthTextView;
    private ObserverTextView dexterityTextView;
    private ObserverTextView constitutionTextView;
    private ObserverTextView intelligenceTextView;
    private ObserverTextView wisdomTextView;
    private ObserverTextView charismaTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attribute_edit;
    }

    @Override
    protected int getHeading() {
        return R.string.attribute_title;
    }

    @Override
    protected void setData() {
        getViews();
        strengthModel = new AttributeModel(character.getStrength());
        strengthNumberView.setController(new AttributeNumberViewController(strengthModel));
        strengthTextView.setModel(new AttributeModelObserver(strengthModel, ruleService, displayService));

        dexterityModel = new AttributeModel(character.getDexterity());
        dexterityNumberView.setController(new AttributeNumberViewController(dexterityModel));
        dexterityTextView.setModel(new AttributeModelObserver(dexterityModel, ruleService, displayService));

        constitutionModel = new AttributeModel(character.getConstitution());
        constitutionNumberView.setController(new AttributeNumberViewController(constitutionModel));
        constitutionTextView.setModel(new AttributeModelObserver(constitutionModel, ruleService, displayService));

        intelligenceModel = new AttributeModel(character.getIntelligence());
        intelligenceNumberView.setController(new AttributeNumberViewController(intelligenceModel));
        intelligenceTextView.setModel(new AttributeModelObserver(intelligenceModel, ruleService, displayService));

        wisdomModel = new AttributeModel(character.getWisdom());
        wisdomNumberView.setController(new AttributeNumberViewController(wisdomModel));
        wisdomTextView.setModel(new AttributeModelObserver(wisdomModel, ruleService, displayService));

        charismaModel = new AttributeModel(character.getCharisma());
        charismaNumberView.setController(new AttributeNumberViewController(charismaModel));
        charismaTextView.setModel(new AttributeModelObserver(charismaModel, ruleService, displayService));

    }

    private void getViews() {
        strengthNumberView = findViewById(R.id.attribute_str);
        dexterityNumberView = findViewById(R.id.attribute_dex);
        constitutionNumberView = findViewById(R.id.attribute_con);
        intelligenceNumberView = findViewById(R.id.attribute_int);
        wisdomNumberView = findViewById(R.id.attribute_wis);
        charismaNumberView = findViewById(R.id.attribute_cha);

        strengthTextView = findViewById(R.id.modifier_str);
        dexterityTextView = findViewById(R.id.modifier_dex);
        constitutionTextView = findViewById(R.id.modifier_con);
        intelligenceTextView = findViewById(R.id.modifier_int);
        wisdomTextView = findViewById(R.id.modifier_wis);
        charismaTextView = findViewById(R.id.modifier_cha);
    }

    @Override
    protected void saveData() {
        character.setStrength(strengthModel.getAttributeValue());
        character.setDexterity(dexterityModel.getAttributeValue());
        character.setConstitution(constitutionModel.getAttributeValue());
        character.setIntelligence(intelligenceModel.getAttributeValue());
        character.setWisdom(wisdomModel.getAttributeValue());
        character.setCharisma(charismaModel.getAttributeValue());
    }

}
