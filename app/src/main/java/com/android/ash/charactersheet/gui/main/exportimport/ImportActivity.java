package com.android.ash.charactersheet.gui.main.exportimport;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_CHARACTER_FILE_PREFIX;
import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_EQUIPMENT_FILE_PREFIX;
import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_FILE_SUFFIX;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.FavoriteCharacterSkill;
import com.android.ash.charactersheet.gui.util.FileComparator;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.FileListAdapter;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.Note;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.ExportImportService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.ItemService;
import com.d20charactersheet.framework.boc.service.exportimport.ImportMessage;
import com.d20charactersheet.framework.boc.service.exportimport.ImportReport;
import com.d20charactersheet.framework.boc.service.exportimport.ImportMessage.Type;
import com.d20charactersheet.framework.boc.util.ImportReportComparator;

/**
 * Allows to select a file to import characters. Displays the import directory and all files starting with
 * d20_characters located in it. By touching an file the user has to confirm to import this file. The file is imported
 * if the user confirms.
 */
public class ImportActivity extends LogActivity implements OnItemClickListener {

    private static final String EMPTY_SPACE = " ";
    private static final Object COLON = ": ";

    private GameSystem gameSystem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        createLayout();
    }

    private void createLayout() {
        setContentView(R.layout.activity_import);
        setTitle(R.string.import_title);

        createImportDirectoryTextView();
        createFilesListView();
    }

    private void createImportDirectoryTextView() {
        final StringBuilder text = new StringBuilder();
        text.append(getResources().getString(R.string.import_import_directory));
        text.append(" ");
        text.append(DirectoryAndFileHelper.getExportDirectory().getPath());

        final TextView textView = (TextView) findViewById(R.id.import_import_directory);
        textView.setText(text.toString());
    }

    private void createFilesListView() {
        final List<File> files = getImportFiles();
        Collections.sort(files, new FileComparator());
        final FileListAdapter adapter = new FileListAdapter(this, R.layout.listitem_name, new ListModel<File>(files));

        final ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(this);

    }

    private List<File> getImportFiles() {
        return Arrays.asList(DirectoryAndFileHelper.getBackupDirectory().listFiles(new ImportFilenameFilter()));
    }

    private ListView getListView() {
        final ListView listView = (ListView) findViewById(android.R.id.list);
        return listView;
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final File importFile = (File) adapterView.getItemAtPosition(position);
        confirmRestore(importFile);
    }

    /**
     * Filters files which name starts with d20cs_characters and end with xml.
     */
    public static class ImportFilenameFilter implements FilenameFilter {

        @Override
        public boolean accept(final File dir, final String name) {
            if (name.endsWith(EXPORT_FILE_SUFFIX)
                    && (name.startsWith(EXPORT_CHARACTER_FILE_PREFIX) || name.startsWith(EXPORT_EQUIPMENT_FILE_PREFIX))) {
                return true;
            }
            return false;
        }
    }

    private void confirmRestore(final File importFile) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.import_dialog_title);
        builder.setMessage(getMessage(importFile));
        builder.setPositiveButton(R.string.import_dialog_import_button, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                importFile(importFile);
            }

        });
        builder.setNegativeButton(R.string.import_dialog_cancel_button, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                // user cancelled the dialog
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String getMessage(final File importFile) {
        final StringBuilder message = new StringBuilder();
        message.append(getResources().getString(R.string.import_dialog_message));
        message.append(EMPTY_SPACE);
        message.append(importFile.getName());
        return message.toString();
    }

    private void importFile(final File importFile) {
        if (importFile.getName().startsWith(EXPORT_CHARACTER_FILE_PREFIX)) {
            new ImportCharacter(this).importCharacters(importFile);
        } else if (importFile.getName().startsWith(EXPORT_EQUIPMENT_FILE_PREFIX)) {
            new ImportEquipment(this, gameSystem.getDisplayService()).importEquipment(importFile);
        }
    }

    private String getMessage(final int resourceId, final String text) {
        final StringBuilder message = new StringBuilder();
        message.append(getResources().getString(resourceId));
        message.append(COLON);
        message.append(text);
        return message.toString();
    }

    private void displayImportReports(final List importReports) {
        setContentView(R.layout.activity_import_reports);
        Collections.sort(importReports, new ImportReportComparator());
        final List<ImportMessage> importMessages = getImportMessages(importReports);

        final ImportMessageAdapter adapter = new ImportMessageAdapter(this, R.layout.listitem_importmessage,
                new ListModel<ImportMessage>(importMessages));

        final ListView listView = getListView();
        listView.setAdapter(adapter);
    }

    private List<ImportMessage> getImportMessages(final List<ImportReport<? super Object>> importReports) {
        final List<ImportMessage> importMessages = new ArrayList<ImportMessage>();
        for (final ImportReport<? extends Object> importReport : importReports) {
            final ImportMessage resultMessage = getResultMessage(importReport);
            importMessages.add(resultMessage);
            importMessages.addAll(importReport.getImportMessages());
        }
        return importMessages;
    }

    private ImportMessage getResultMessage(final ImportReport<? extends Object> importReport) {
        ImportMessage resultMessage;
        if (importReport.isSuccess()) {
            resultMessage = getResultMessage(importReport, R.string.importmessage_import_success,
                    ImportMessage.Type.SUCCESS);
        } else {
            resultMessage = getResultMessage(importReport, R.string.importmessage_import_failure,
                    ImportMessage.Type.FAILURE);
        }
        return resultMessage;
    }

    private ImportMessage getResultMessage(final ImportReport<? extends Object> importReport, final int resourceId,
            final Type type) {
        final StringBuilder message = new StringBuilder();
        message.append(importReport.getImportObject().toString());
        message.append(EMPTY_SPACE);
        message.append(getString(resourceId));
        final ImportMessage resultMessage = new ImportMessage(message.toString(), type);
        return resultMessage;
    }

    /**
     * Imports characters and creates them in the game system.
     */
    public class ImportCharacter {

        private final Context context;

        /**
         * Creates ImportCharacter.
         * 
         * @param context
         *            The context of the activity.
         */
        public ImportCharacter(final Context context) {
            this.context = context;
        }

        /**
         * Imports characters from the given file and creates them in the game system.
         * 
         * @param importFile
         *            The file to import.
         */
        public void importCharacters(final File importFile) {
            Logger.debug("importCharacters");
            final ExportImportService exportImportService = gameSystem.getExportImportService();
            try {
                final List<ImportReport<Character>> importReports = exportImportService.importCharacters(gameSystem,
                        importFile);
                createCharacters(importReports);
            } catch (final Exception exception) {
                Logger.error("Can't import characters: " + importFile, exception);
                final String message = getMessage(R.string.import_message_import_failure, exception.getMessage());
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        private void createCharacters(final List<ImportReport<Character>> importReports) {
            Logger.debug("importReports: " + importReports);
            for (final ImportReport<Character> importReport : importReports) {
                if (importReport.isSuccess()) {
                    createCharacter(importReport.getImportObject());
                }
            }
            displayImportReports(importReports);
        }

        private void createCharacter(final Character character) {
            final CharacterService characterService = gameSystem.getCharacterService();
            final List<CharacterSkill> characterSkills = character.getCharacterSkills();

            characterService.createCharacter(character, gameSystem.getAllSkills());
            createCharacterSkills(character, characterSkills, characterService);
            createNotes(character, characterService);
            createWeaponAttacks(character, characterService);
            characterService.updateFeats(character);
            createKnownSpells(character, characterService);
            createSpellSlots(character, characterService);
            characterService.updateWeapons(character, character.getEquipment().getWeapons());
            characterService.updateArmor(character, character.getEquipment().getArmor());
            characterService.updateGoods(character, character.getEquipment().getGoods());
        }

        private void createKnownSpells(final Character character, final CharacterService characterService) {
            final List<KnownSpell> knownSpells = character.getKnownSpells();
            character.setKnownSpells(new ArrayList<KnownSpell>(knownSpells.size()));
            for (final KnownSpell knownSpell : knownSpells) {
                characterService.createKnownSpell(character, knownSpell);
            }
        }

        private void createSpellSlots(final Character character, final CharacterService characterService) {
            final List<SpellSlot> spellSlots = character.getSpellSlots();
            character.setSpellSlots(new ArrayList<SpellSlot>(spellSlots.size()));
            for (final SpellSlot spellSlot : spellSlots) {
                characterService.createSpellSlot(character, spellSlot);
            }
        }

        private void createCharacterSkills(final Character character, final List<CharacterSkill> characterSkills,
                final CharacterService characterService) {
            final List<FavoriteCharacterSkill> favCharacterSkills = new ArrayList<FavoriteCharacterSkill>(
                    characterSkills.size());

            // create list of FavoriteCharacterSkill
            for (final CharacterSkill characterSkill : characterSkills) {
                final Skill skill = characterSkill.getSkill();
                final FavoriteCharacterSkill favoriteCharacterSkill = new FavoriteCharacterSkill(skill);
                favoriteCharacterSkill.setRank(characterSkill.getRank());
                favoriteCharacterSkill.setModifier(characterSkill.getModifier());
                favoriteCharacterSkill.setFavorite(true);
                favCharacterSkills.add(favoriteCharacterSkill);
            }

            // remove FavoriteCharacterSkill from all character skills
            final List<CharacterSkill> newCharacterSkills = new ArrayList<CharacterSkill>(character
                    .getCharacterSkills().size());
            for (final CharacterSkill characterSkill : character.getCharacterSkills()) {
                if (!contains(characterSkill, favCharacterSkills)) {
                    newCharacterSkills.add(characterSkill);
                }
            }

            // complete list of character skills and save them
            newCharacterSkills.addAll(favCharacterSkills);
            character.setCharacterSkills(newCharacterSkills);
            for (final CharacterSkill characterSkill : newCharacterSkills) {
                characterService.updateCharacterSkill(character, characterSkill);
            }
        }

        private boolean contains(final CharacterSkill characterSkill,
                final List<FavoriteCharacterSkill> favCharacterSkills) {
            for (final FavoriteCharacterSkill favoriteCharacterSkill : favCharacterSkills) {
                if (characterSkill.getSkill().equals(favoriteCharacterSkill.getSkill())) {
                    return true;
                }
            }
            return false;
        }

        private void createNotes(final Character character, final CharacterService characterService) {
            final List<Note> notes = character.getNotes();
            character.setNotes(new ArrayList<Note>(notes.size()));
            for (final Note note : notes) {
                characterService.createNote(note, character);
            }
        }

        private void createWeaponAttacks(final Character character, final CharacterService characterService) {
            final List<WeaponAttack> weaponAttacks = character.getWeaponAttacks();
            character.setWeaponAttacks(new ArrayList<WeaponAttack>(weaponAttacks.size()));
            for (final WeaponAttack weaponAttack : weaponAttacks) {
                characterService.createWeaponAttack(character, weaponAttack);
            }
        }

    }

    /**
     * Imports equipment from a file and creates it in the game system.
     */
    public class ImportEquipment {

        private final Context context;
        private final DisplayService displayService;

        private final List<String> allWeaponNames;
        private final List<String> allArmorNames;
        private final List<String> allGoodNames;

        /**
         * Creates ImportEquipment. Creates list of names of all weapons, armor and goods.
         * 
         * @param context
         *            The context of the app.
         * @param displayService
         *            The service to display data.
         */
        public ImportEquipment(final Context context, final DisplayService displayService) {
            this.context = context;
            this.displayService = displayService;
            allWeaponNames = getAllNames(gameSystem.getAllWeapons());
            allArmorNames = getAllNames(gameSystem.getAllArmor());
            allGoodNames = getAllNames(gameSystem.getAllGoods());
        }

        private List<String> getAllNames(final List<? extends Item> items) {
            final List<String> allNames = new LinkedList<String>();
            for (final Item item : items) {
                allNames.add(displayService.getDisplayItem(item));
            }
            return allNames;
        }

        /**
         * Imports equipment form the given file and creates it in the game system.
         * 
         * @param importFile
         *            The file to import.
         */
        public void importEquipment(final File importFile) {
            Logger.debug("importEquipment");
            final ExportImportService exportImportService = gameSystem.getExportImportService();
            try {
                final List<ImportReport<? extends Item>> importReports = exportImportService.importEquipment(
                        gameSystem, importFile);
                final boolean isNewItemCreated = createItems(importReports);
                if (isNewItemCreated) {
                    gameSystem.clear();
                }
            } catch (final Exception exception) {
                Logger.error("Can't import equipment: " + importFile, exception);
                final String message = getMessage(R.string.import_message_import_failure, exception.getMessage());
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        private boolean createItems(final List<ImportReport<? extends Item>> importReports) {
            Logger.debug("importReports: " + importReports);
            final ItemService itemService = gameSystem.getItemService();
            boolean isNewItemCreated = false;
            for (final ImportReport<? extends Item> importReport : importReports) {
                createItem(importReport, itemService);
                if (importReport.isSuccess()) {
                    isNewItemCreated = true;
                }
            }
            displayImportReports(importReports);
            return isNewItemCreated;
        }

        private void createItem(final ImportReport<? extends Item> importReport, final ItemService itemService) {
            final Item item = importReport.getImportObject();
            if (item instanceof Weapon) {
                createWeapon(importReport, itemService);
            } else if (item instanceof Armor) {
                createArmor(importReport, itemService);
            } else if (item instanceof Good) {
                createGood(importReport, itemService);
            } else {
                throw new IllegalArgumentException("Unknown class of item: " + item);
            }
        }

        private void createWeapon(final ImportReport<? extends Item> importReport, final ItemService itemService) {
            final Weapon weapon = (Weapon) importReport.getImportObject();
            if (allWeaponNames.contains(displayService.getDisplayItem(weapon))) {
                importReport.addMessage(getDuplicateItemMessage(weapon.getName()));
            } else {
                itemService.createWeapon(weapon);
            }
        }

        private void createArmor(final ImportReport<? extends Item> importReport, final ItemService itemService) {
            final Armor armor = (Armor) importReport.getImportObject();
            if (allArmorNames.contains(displayService.getDisplayItem(armor))) {
                importReport.addMessage(getDuplicateItemMessage(armor.getName()));
            } else {
                itemService.createArmor(armor);
            }
        }

        private void createGood(final ImportReport<? extends Item> importReport, final ItemService itemService) {
            final Good good = (Good) importReport.getImportObject();
            if (allGoodNames.contains(displayService.getDisplayItem(good))) {
                importReport.addMessage(getDuplicateItemMessage(good.getName()));
            } else {
                itemService.createGood(good);
            }
        }

        private ImportMessage getDuplicateItemMessage(final String name) {
            final String message = "Item named " + name + " already exists. Ignoring it";
            final ImportMessage importMessage = new ImportMessage(message, ImportMessage.Type.ERROR);
            return importMessage;
        }

    }
}
