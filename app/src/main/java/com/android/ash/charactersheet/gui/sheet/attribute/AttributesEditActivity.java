package com.android.ash.charactersheet.gui.sheet.attribute;

import android.os.Bundle;

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
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.activity_attribute_edit);
        super.onCreate(savedInstanceState);
        setTitle(R.string.attribute_title);

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
        strengthNumberView = (StepNumberView) findViewById(R.id.attribute_str);
        dexterityNumberView = (StepNumberView) findViewById(R.id.attribute_dex);
        constitutionNumberView = (StepNumberView) findViewById(R.id.attribute_con);
        intelligenceNumberView = (StepNumberView) findViewById(R.id.attribute_int);
        wisdomNumberView = (StepNumberView) findViewById(R.id.attribute_wis);
        charismaNumberView = (StepNumberView) findViewById(R.id.attribute_cha);

        strengthTextView = (ObserverTextView) findViewById(R.id.modifier_str);
        dexterityTextView = (ObserverTextView) findViewById(R.id.modifier_dex);
        constitutionTextView = (ObserverTextView) findViewById(R.id.modifier_con);
        intelligenceTextView = (ObserverTextView) findViewById(R.id.modifier_int);
        wisdomTextView = (ObserverTextView) findViewById(R.id.modifier_wis);
        charismaTextView = (ObserverTextView) findViewById(R.id.modifier_cha);
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
