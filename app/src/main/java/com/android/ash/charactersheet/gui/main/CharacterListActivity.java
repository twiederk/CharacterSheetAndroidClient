package com.android.ash.charactersheet.gui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.PreferenceServiceHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.boc.service.PreferenceServiceImpl;
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao;
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.admin.AdministrationMenuActivity;
import com.android.ash.charactersheet.gui.main.exportimport.ExportMenuActivity;
import com.android.ash.charactersheet.gui.main.exportimport.ImportActivity;
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.CharacterComparator;

import java.io.File;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * The list of characters available. Each character is listed with an image, its name, race, class and level.
 */
public class CharacterListActivity extends LogAppCompatActivity implements OnItemClickListener,
        AbstractAsyncTask.GameSystemLoadable {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<PreferenceServiceHolder> preferenceServiceHolder = inject(PreferenceServiceHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);

    private static final int CONTEXT_MENU_DELETE_CHARACTER = 10;

    private static final String DNDV35_OLD_DATABASE_NAME = "character_db";

    private List<Character> characters;
    private CharacterArrayAdapter adapter;

    private GameSystemType gameSystemType = GameSystemType.DNDV35;

    private DBHelper dndDbHelper;
    private DBHelper pathfinderDbHelper;

    private GameSystem gameSystem;
    private PreferenceService preferenceService;

    private boolean showedReleaseNotes;

    /**
     * Creates list of all available character. The characters are retrieved from the CharacterDao implementation.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renameDnDv35Database();

        gameSystem = gameSystemHolder.getValue().getGameSystem();

        if (gameSystem == null) {
            final int dbVersion = BuildConfig.VERSION_CODE;
            dndDbHelper = new DBHelper(this, GameSystemType.DNDV35.getDatabaseName(), dbVersion,
                    GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                    GameSystemType.DNDV35.getImages());
            pathfinderDbHelper = new DBHelper(this, GameSystemType.PATHFINDER.getDatabaseName(), dbVersion,
                    GameSystemType.PATHFINDER.getCreateScripts(), GameSystemType.PATHFINDER.getUpdateScripts(),
                    GameSystemType.PATHFINDER.getImages());

            preferenceService = new PreferenceServiceImpl(new AndroidPreferenceDao(this));
            preferenceServiceHolder.getValue().setPreferenceService(preferenceService);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final GameSystem gameSystem = gameSystemHolder.getValue().getGameSystem();
        if (gameSystem == null) {
            gameSystemType = getGameSystemTypeFromPreferences();
            new CreateDatabaseAndGameSystemAsyncTask(this, this, dndDbHelper, pathfinderDbHelper, gameSystemType)
                    .execute();
        } else if (!gameSystem.isLoaded()) {
            new LoadGameSystemAsyncTask(this, this, gameSystem).execute();
        } else {
            onResumeLayout();
        }
    }

    private GameSystemType getGameSystemTypeFromPreferences() {
        final int gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        for (final GameSystemType gameSystemType : GameSystemType.values()) {
            if (gameSystemType.ordinal() == gameSystemTypeId) {
                return gameSystemType;
            }
        }
        throw new IllegalStateException("Can't determine game system with id: " + gameSystemTypeId);
    }

    @Override
    public void onItemClick(final AdapterView<?> listView, final View view, final int position, final long id) {

        // get selected character
        final Character character = characters.get(position);
        Logger.debug("character: " + character);

        // store character
        characterHolder.getValue().setCharacter(character);

        // call character sheet
        final Intent intent = new Intent(this, CharacterSheetActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_character_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_activity_character_list_new_character:
                return callActivity(CharacterCreateActivity.class);

            case R.id.menu_activity_character_list_about:
                return callActivity(AboutActivity.class);

            case R.id.menu_activity_character_list_administration:
                return callActivity(AdministrationMenuActivity.class);

            case R.id.menu_activity_character_list_preference:
                return callActivity(PreferencesActivity.class);

            case R.id.menu_activity_character_list_switch_game_system:
                return switchGameSystem();

            case R.id.menu_activity_character_list_backup_restore:
                return callActivity(BackupRestoreActivity.class);

            case R.id.menu_activity_character_list_export:
                return callActivity(ExportMenuActivity.class);

            case R.id.menu_activity_character_list_import:
                return callActivity(ImportActivity.class);

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @SuppressWarnings("SameReturnValue")
    private boolean switchGameSystem() {
        DBHelper dbHelper;
        if (gameSystemType.equals(GameSystemType.DNDV35)) {
            gameSystemType = GameSystemType.PATHFINDER;
            dbHelper = pathfinderDbHelper;
        } else {
            gameSystemType = GameSystemType.DNDV35;
            dbHelper = dndDbHelper;
        }
        new SwitchGameSystemAsyncTask(this, this, dbHelper, gameSystemType).execute();
        return true;
    }

    @SuppressWarnings("SameReturnValue")
    private boolean callActivity(final Class<? extends Activity> activityClass) {
        final Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        return true;
    }

    /**
     * Creates a context menu displaying a delete option of the selected character.
     *
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
     * android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final Character character = (Character) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(character.getName());
        menu.add(0, CONTEXT_MENU_DELETE_CHARACTER, 0, R.string.character_list_delete);
    }

    private ListView getListView() {
        return findViewById(android.R.id.list);
    }

    /**
     * Deletes the character if the delete option is selected.
     *
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        if (item.getItemId() == CONTEXT_MENU_DELETE_CHARACTER) {
            deleteCharacter(getCharacter(item));
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private Character getCharacter(final MenuItem item) {
        final AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
        return (Character) getListView().getAdapter().getItem(menuInfo.position);
    }

    private void deleteCharacter(final Character character) {
        Logger.debug("deleteCharacter");
        Logger.debug("character: " + character);
        Objects.requireNonNull(gameSystem).deleteCharacter(character);
        adapter.remove(character);
    }

    /**
     * Fills list view with characters.
     */
    private void onResumeLayout() {
        characters = gameSystem.getAllCharacters();
        final DisplayService displayService = gameSystem.getDisplayService();
        final AndroidImageService imageService = (AndroidImageService) gameSystem.getImageService();
        adapter = new CharacterArrayAdapter(this, displayService, imageService, R.layout.listitem_character, characters);
        adapter.sort(new CharacterComparator());
        final ListView listView = getListView();
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
    }

    private void showReleaseNotes() {
        final View releaseNotesView = findViewById(R.id.character_list_release_notes);
        releaseNotesView.setVisibility(View.VISIBLE);

        final TextView releaseNotesTextView = findViewById(R.id.list_of_release_notes);
        releaseNotesTextView.setText(getReleaseNotes());
    }

    private String getReleaseNotes() {
        final StringBuilder releaseNotes = new StringBuilder();
        switch (dndDbHelper.getOldVersion()) {
            case 1:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_1_0));
            case 2:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_2_0));
            case 3:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_3_0));
            case 4:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_4_0));
            case 5:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_5_0));
            case 6:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_6_0));
            case 7:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_7_0));
            case 8:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_8_0));
            case 9:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_9_0));
            case 10:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_10_0));
            case 11:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_11_0));
            case 12:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_11_1));
            case 13:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_12_0));
            case 14:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_12_1));
            case 15:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_13_0));
            case 16:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_14_0));
            case 17:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_15_0));
            case 18:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_16_0));
            case 19:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_17_0));
            case 20:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_18_0));
            case 21:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_19_0));
            case 22:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_19_1));
            case 23:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_20_0));
            case 24:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_1_21_0));
            case 25:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_0_0));
            case 26:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_0_1));
            case 27:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_1_0));
            case 28:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_2_0));
            case 29:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_2_1));
            case 30:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_3_0));
            case 31:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_4_0));
            case 32:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_5_0));
            case 33:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_6_0));
            case 34:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_6_1));
            case 35:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_7_0));
            case 36:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_8_0));
            case 37:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_9_0));
            case 38:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_10_0));
            case 39:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_0));
            case 40:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_1));
            case 41:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_2));
            case 42:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_3));
            case 43:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_4));
            case 44:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_5));
            case 45:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_6));
            case 46:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_7));
            case 47:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_8));
            case 48:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_2_11_9));
                break;

            default:
                releaseNotes.insert(0, getResources().getString(R.string.release_notes_not_found));
                break;
        }
        return releaseNotes.toString();
    }

    @Override
    public void onGameSystemLoaded() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();

        setContentView(R.layout.activity_character_list);

        setToolbar();
        setReleaseNotes();
        setOkButton();
        onResumeLayout();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(createTitle());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private String createTitle() {
        return getResources().getString(R.string.character_list_title) + " - " + gameSystem.getRuleService().getName();
    }

    private void setReleaseNotes() {
        if (dndDbHelper.isUpgrade() && !showedReleaseNotes) {
            showReleaseNotes();
            showedReleaseNotes = true;
        }
    }

    private void setOkButton() {
        final Button okButton = findViewById(R.id.button_ok);
        okButton.setOnClickListener(v -> {
            final View releaseNotesView = findViewById(R.id.character_list_release_notes);
            releaseNotesView.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dndDbHelper.close();
        pathfinderDbHelper.close();
    }

    private void renameDnDv35Database() {
        final File oldDatabase = getDatabasePath(DNDV35_OLD_DATABASE_NAME);
        if (oldDatabase != null && oldDatabase.exists()) {
            final File newDatabase = getDatabasePath(GameSystemType.DNDV35.getDatabaseName());
            if (oldDatabase.renameTo(newDatabase)) {
                Logger.warn("Failed to rename " + oldDatabase + " to " + newDatabase);
            }
        }
    }

}
